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
public class Trazabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long envioId;
    private String mensaje;
    private LocalDateTime fecha = LocalDateTime.now();
}
