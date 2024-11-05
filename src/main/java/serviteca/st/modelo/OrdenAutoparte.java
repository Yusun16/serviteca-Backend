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

public class OrdenAutoparte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn (name = "autoparte_id")
    private Autoparte autoparte;

    @ManyToOne
    @JoinColumn (name = "orden_id")
    private Orden orden;
}