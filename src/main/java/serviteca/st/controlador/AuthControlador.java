package serviteca.st.controlador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.Dto.ChangePassword;
import serviteca.st.modelo.Dto.MailBody;
import serviteca.st.modelo.ERole;
import serviteca.st.modelo.ForgotPassword;
import serviteca.st.modelo.Role;
import serviteca.st.modelo.User;
import serviteca.st.repositorio.ForgotPasswordRepositorio;
import serviteca.st.repositorio.RoleRepositorio;
import serviteca.st.repositorio.UserRepositorio;
import serviteca.st.request.LoginRequest;
import serviteca.st.request.SignupRequest;
import serviteca.st.response.JwtResponse;
import serviteca.st.response.MessageResponse;
import serviteca.st.seguridad.jwt.JwtUtils;
import serviteca.st.seguridad.user.UserDetailsImpl;
import serviteca.st.servicio.email.EmailServicio;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/servitecauth/")
public class AuthControlador {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepositorio userRepositorio;

    @Autowired
    RoleRepositorio roleRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailServicio emailServicio;

    @Autowired
    ForgotPasswordRepositorio forgotPasswordRepositorio;

    @GetMapping("/loginnn")
    public ResponseEntity<?> loginnn() {
        return new ResponseEntity<>("Hello, Welcome to Serviteca", HttpStatus.OK);
    };

    @PostMapping("/signup")
    public ResponseEntity<?> registeruser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepositorio.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este Usuario ya existe!"));
        }

        User user = new User(signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()));

        user.setCedula(signupRequest.getCedula());

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepositorio.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: El role no existe o no se encuentra registrado!"));
            roles.add(userRole);
        } else  {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepositorio.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: El role no se encuentra!"));
                        roles.add(adminRole);
                        break;
                    case "jefeTaller":
                        Role jefeTallerRole = roleRepositorio.findByName(ERole.ROLE_TALLER)
                                .orElseThrow(() -> new RuntimeException("Error: El role no se encuentra!"));
                        roles.add(jefeTallerRole);
                        break;
                    case "jefeInventario":
                        Role jefeInventarioRole = roleRepositorio.findByName(ERole.ROLE_INVENTARIO)
                                .orElseThrow(() -> new RuntimeException("Error: El role no se encuentra!"));
                        roles.add(jefeInventarioRole);
                        break;
                    case "asistenteCompra":
                        Role asistenteCompraRole = roleRepositorio.findByName(ERole.ROLE_ASISTENTE)
                                .orElseThrow(() -> new RuntimeException("Error: El role no se encuentra!"));
                        roles.add(asistenteCompraRole);
                        break;
                    default:
                        Role userRole = roleRepositorio.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: El role no se encuentra!"));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepositorio.save(user);

        return ResponseEntity.ok().body(new MessageResponse("Registrado exitosamente!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> autenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,  userDetails.getUsername(), userDetails.getId(), roles));
    }

    @PostMapping("/verify-correo")
    public ResponseEntity<String> verifyEmail(@RequestParam String username, @RequestParam String cedula) {
        User user = userRepositorio.findByUsername(username).orElseThrow(() -> new RuntimeException("Please provide an valid username!"));

        if (!user.getCedula().equals(cedula)) {
            return ResponseEntity.badRequest().body("Error: cedula no encontrada o no valida:" + cedula);
        }

        // Verificar si existe un OTP no se ha acabado
        Optional<ForgotPassword> existingOtp = forgotPasswordRepositorio.findByUser(user);
        if (existingOtp.isPresent()) {
            // Si el OTP aún es válido, podemos avisarle al usuario
            if (!existingOtp.get().getTiempoEspera().before(Date.from(Instant.now()))) {
                return ResponseEntity.ok("Ya has solicitado un OTP. Puedes solicitar uno nuevo en 60 segundos.");
            }
        }

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(username)
                .text("Cordial Saludo! Usa este codigo para confirmar el correo, para que puedas resetear tu contrasena. El codigo OTP es: " + otp)
                .subject("Verificacion del correo")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .tiempoEspera(new Date(System.currentTimeMillis() + 60 * 1000))
                .user(user)
                .build();

        emailServicio.sendSimpleMessage(mailBody);

        forgotPasswordRepositorio.save(fp);

        return ResponseEntity.ok("Email sent for verification!");
    }

    // OTP verification
//    @PostMapping("/verifyOtp/{otp}/{username}")
//    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String username) {
    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestParam String otp, @RequestParam String username) {
        Integer otpInteger = Integer.valueOf(otp);

        User user = userRepositorio.findByUsername(username).orElseThrow(() -> new RuntimeException("Please provide an valid email!"));

        ForgotPassword fp = forgotPasswordRepositorio.findByOtpAndUsername(otpInteger, user).orElseThrow(() -> new RuntimeException("Invalid OTP for username : " + username));

        if (fp.getTiempoEspera().before(Date.from(Instant.now()))) {
            forgotPasswordRepositorio.deleteById(fp.getId());
            return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP verified!");
    }

    // set the new password
    @PostMapping("/changePassword/{otp}/{username}")
    public ResponseEntity<?> forgotPasswordHandler(@RequestBody ChangePassword changePassword,
                                                   @PathVariable String otp,
                                                   @PathVariable String username) {
        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
        }

        Integer otpInteger = Integer.valueOf(otp);

        User user = userRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Please provide a valid email!"));

        ForgotPassword fp = forgotPasswordRepositorio.findByOtpAndUsername(otpInteger, user)
                .orElseThrow(() -> new RuntimeException("Invalid OTP for username: " + username));

        if (fp.getTiempoEspera().before(Date.from(Instant.now()))) {
            forgotPasswordRepositorio.deleteById(fp.getId());
            return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());

        userRepositorio.updatePassword(username, encodedPassword);

        return ResponseEntity.ok("Password has been changed!");
    }


    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
