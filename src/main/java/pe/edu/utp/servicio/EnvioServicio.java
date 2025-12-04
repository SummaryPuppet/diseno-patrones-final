package pe.edu.utp.servicio;

import pe.edu.utp.dominio.Envio;
import pe.edu.utp.state.EnvioContexto;

import java.util.Optional;

public interface EnvioServicio {
    Envio crearEnvio(Envio envio);
    Optional<Envio> findById(Long id) ;
    Envio avanceEstado(Long id);
    void programarRecogida(Envio envio);
    void cancelarRecogida(Envio envio);
}
