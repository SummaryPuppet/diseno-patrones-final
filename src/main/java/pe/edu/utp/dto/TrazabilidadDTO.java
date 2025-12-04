package pe.edu.utp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrazabilidadDTO {
    private Long id;
    private Long envioId;
    private String mensaje;
    private LocalDateTime fecha;
}
