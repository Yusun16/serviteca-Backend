package serviteca.st.controlador;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.ERole;
import serviteca.st.modelo.Role;
import serviteca.st.modelo.User;
import serviteca.st.repositorio.RoleRepositorio;
import serviteca.st.repositorio.UserRepositorio;
import serviteca.st.request.SignupRequest;
import serviteca.st.response.MessageResponse;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/servitecacess/")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class InicioControlador {

    @Autowired
    UserRepositorio userRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepositorio roleRepositorio;

    @GetMapping("/bienvenidos")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String bienvenidos() {
        return "Bienvenidos a Serviteca";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Administrador";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "Usuario";
    }

    @GetMapping("/jefeTaller")
    @PreAuthorize("hasRole('TALLER')")
    public String tallerAccess() {
        return "Jefe de Taller";
    }

    @GetMapping("/jefeInventario")
    @PreAuthorize("hasRole('INVENTARIO')")
    public String inventarioAccess() {
        return "Jefe de Inventario";
    }

    @GetMapping("/asistenteCompra")
    @PreAuthorize("hasRole('ASISTENTE')")
    public String asistenteAccess() {
        return "Asistente de Compra";
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
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
}
