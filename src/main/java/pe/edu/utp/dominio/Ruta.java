package pe.edu.utp.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origen;
    private String destino;
    private Double distanciaKm;
    private Double tiempoEstimadoHoras;
    private String tipo; // e.g., "CORTA", "RAPIDA"
}
