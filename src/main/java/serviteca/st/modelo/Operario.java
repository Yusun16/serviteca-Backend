package serviteca.st.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
// @Table(name = "operario")
public class Operario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;
    String cedula;
    String nombre;
    String apellido;
    String correo;
    String telefono;
    String direccion;
    String estado;
    String fotoUrl;
    String acudiente;
    String telefonoAcudiente;
    String especialidad;

}
