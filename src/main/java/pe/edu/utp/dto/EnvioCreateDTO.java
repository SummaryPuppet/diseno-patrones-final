package pe.edu.utp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.utp.dominio.EnvioTipo;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EnvioCreateDTO {
    private EnvioTipo envioTipo;
    private String origen;
    private String destino;
    private Long rutaId;
}
