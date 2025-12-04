package pe.edu.utp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RutaDTO {
    private Long id;
    private String origen;
    private String destino;
    private Double distanciaKm;
    private Double tiempoEstimadoHoras;
    private String tipo;
}
