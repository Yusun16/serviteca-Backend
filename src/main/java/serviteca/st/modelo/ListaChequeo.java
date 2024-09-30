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

public class ListaChequeo {

    public enum combustible{E,M,F}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idLista;
    @Column(nullable = false,columnDefinition = "LONGTEXT")
    String Foto;
    String observacion;
    combustible combustible;
    @ManyToOne
    Orden orden;

}
