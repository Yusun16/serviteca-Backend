package serviteca.st.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data // Incluye @Getter y @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String cedula;
    String nombre;
    String apellido;
    String correo;
    String direccion;
    Double telefono;
    String departamento;
    String ciudad;
    String razonSocial;
    Integer nit;

}
