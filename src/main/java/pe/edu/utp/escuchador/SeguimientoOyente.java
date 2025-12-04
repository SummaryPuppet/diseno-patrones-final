package pe.edu.utp.escuchador;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pe.edu.utp.evento.EnvioStateCambioEvento;

@Component
public class SeguimientoOyente {
   @EventListener
    public void manejarEnvioStateCambioEvento(EnvioStateCambioEvento evento) {
         System.out.println("El envío con ID " + evento.getEnvioId() + " ha cambiado al estado: " + evento.getNuevoEstado());
    }
}
