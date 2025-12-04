package pe.edu.utp.state;

import pe.edu.utp.dominio.Envio;

public class CreadoState implements EnvioState {
    @Override
    public void next(EnvioContexto ctx) {
        ctx.setState(new EnCaminoState());
    }

    @Override
    public String name() {
        return "CREADO";
    }

    @Override
    public void aplicarReglaNegocio(Envio envio) {
        envio.setStatus(name());
    }
}
