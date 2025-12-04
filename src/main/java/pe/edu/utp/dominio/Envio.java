package pe.edu.utp.dominio;

import java.time.LocalDateTime;

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
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private EnvioTipo envioTipo;
    private String origen;
    private String destino;
    private Long rutaId;
    private String status;
    private LocalDateTime fecha = LocalDateTime.now();
}
