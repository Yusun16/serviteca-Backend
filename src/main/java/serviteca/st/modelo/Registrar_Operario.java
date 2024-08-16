package serviteca.st.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Registrar_Operario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEmpleado;
    BigInteger Cedula;
    String Nombre;
    String Apellido;
    String Direccion;
    Integer Telefono;
    String Correo;
    String Nombre_Acudiente;
    BigInteger Telefono_Acudiente;
    String Especialidad_Operario;
    @Column(columnDefinition = "LONGTEXT")
    String Foto;

}
