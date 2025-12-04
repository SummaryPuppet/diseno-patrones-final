package pe.edu.utp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.dominio.EnvioTipo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnvioDTO {
    private Long id;
    private EnvioTipo envioTipo;
    private String origen;
    private String destino;
    private Long rutaId;
    private String status;
    private LocalDateTime fecha;
}
