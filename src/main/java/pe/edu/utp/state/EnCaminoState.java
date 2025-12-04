package pe.edu.utp.state;

public class EnCaminoState implements EnvioState {
    @Override
    public void next(EnvioContexto ctx) {
        ctx.setState(new EntregadoState());
    }

    @Override
    public String name() {
        return "EN CAMINO";
    }

    @Override
    public void aplicarReglaNegocio(pe.edu.utp.dominio.Envio envio) {
        envio.setStatus(name());
    }
}
