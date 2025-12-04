package pe.edu.utp.evento;

import org.springframework.context.ApplicationEvent;

public class EnvioStateCambioEvento extends ApplicationEvent {
    private final Long envioId;
    private final String nuevoEstado;

    public EnvioStateCambioEvento(Object source, Long envioId, String nuevoEstado) {
        super(source);
        this.envioId = envioId;
        this.nuevoEstado = nuevoEstado;
    }

    public Long getEnvioId() {
        return envioId;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }
}
