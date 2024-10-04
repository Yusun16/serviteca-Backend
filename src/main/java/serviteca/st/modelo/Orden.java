package serviteca.st.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idOrden;
    String codigo;
    String tipoServicio;
    String placaVehiculo;
    String kilometraje;
    LocalDate fecha;


    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private Cliente clienteId;
}
