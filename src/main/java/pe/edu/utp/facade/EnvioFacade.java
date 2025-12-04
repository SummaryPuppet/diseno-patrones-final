package pe.edu.utp.facade;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.utp.dominio.Envio;
import pe.edu.utp.servicio.EnvioServicio;

@Component
@AllArgsConstructor
public class EnvioFacade {
    private final EnvioServicio envioServicio;

    public Envio crearYProgramarEnvio(Envio envio) {
        Envio nuevoEnvio = envioServicio.crearEnvio(envio);
        envioServicio.programarRecogida(nuevoEnvio);
        return nuevoEnvio;
    }
}
