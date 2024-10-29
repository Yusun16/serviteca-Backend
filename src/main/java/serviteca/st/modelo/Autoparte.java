package serviteca.st.modelo;

import jakarta.persistence.*;
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
    Integer idAupartes;
    String referencia;
    String siigo;
    String descripcion;
    String nombre;
    String linea;
    String tipo;
    String marca;
    String modelo;
    String cantidad;

    @ManyToOne
    @JoinColumn (name = "servicio_id")
    Servicio servicio;

}
