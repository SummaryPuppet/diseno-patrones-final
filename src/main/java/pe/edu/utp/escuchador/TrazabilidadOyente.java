package pe.edu.utp.escuchador;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Trazabilidad;
import pe.edu.utp.evento.EnvioStateCambioEvento;
import pe.edu.utp.servicio.TrazabilidadServicio;

@Component
@AllArgsConstructor
public class TrazabilidadOyente {
    private final TrazabilidadServicio trazabilidadServicio;

    @EventListener
    public void onEnvioStateCambio(EnvioStateCambioEvento evento) {
        Trazabilidad t = new Trazabilidad();
        t.setEnvioId(evento.getEnvioId());
        t.setMensaje("Estado cambiado a: " + evento.getNuevoEstado());
        trazabilidadServicio.registrar(t);
    }
}
