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

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String placa;
    String marca;
    String linea;
    String modelo;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;
    String foto;
    String observacion;

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
