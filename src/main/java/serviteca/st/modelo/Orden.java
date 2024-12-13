package serviteca.st.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idOrden;
    String codigoOrden;
    String cliente;
    String tipoServicio;
    String placaVehiculo;
    String kilometraje;
    Date fecha;
}
