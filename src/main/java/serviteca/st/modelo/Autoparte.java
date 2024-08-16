package serviteca.st.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Autoparte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idAuparte;
    String referencia;
    String siigo;
    String descripcion;
    String linea;
    String tipo;
    String proveedor;
    String marca;
    String modelo;


}
