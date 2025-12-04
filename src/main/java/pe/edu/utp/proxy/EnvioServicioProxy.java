package pe.edu.utp.proxy;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Envio;
import pe.edu.utp.servicio.EnvioServicio;

import java.util.Optional;

@AllArgsConstructor
public class EnvioServicioProxy implements EnvioServicio {
    private EnvioServicio envioServicioReal;

    private void autenticar() {
        // Lógica de autenticación (por ejemplo, verificar tokens, credenciales, etc.)
        System.out.println("Autenticando solicitud...");
    }

    private void loggear(String accion) {
        // Lógica de logging (por ejemplo, registrar en un archivo o sistema de monitoreo)
        System.out.println("Log: " + accion);
    }




    @Override
    public Envio crearEnvio(Envio envio) {
        autenticar();
        loggear("Crear Envío");
        return envioServicioReal.crearEnvio(envio);
    }

    @Override
    public Optional<Envio> findById(Long id) {
        loggear("findById");
        return envioServicioReal.findById(id);
    }

    @Override
    public Envio avanceEstado(Long id) {
        autenticar();
        loggear("Avance de Estado");
        return envioServicioReal.avanceEstado(id);
    }

    @Override
    public void programarRecogida(Envio envio) {
        autenticar();
        loggear("Programar Recogida");
        envioServicioReal.programarRecogida(envio);
    }

    @Override
    public void cancelarRecogida(Envio envio) {
        autenticar();
        loggear("Cancelar Recogida");
        envioServicioReal.cancelarRecogida(envio);
    }
}
