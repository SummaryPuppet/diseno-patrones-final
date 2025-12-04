package pe.edu.utp.state;

public class EntregadoState implements EnvioState {
    @Override
    public void next(EnvioContexto ctx) {
        // No hay siguiente estado después de "ENTREGADO"
    }

    @Override
    public String name() {
        return "ENTREGADO";
    }

    @Override
    public void aplicarReglaNegocio(pe.edu.utp.dominio.Envio envio) {
        envio.setStatus(name());
    }
}
