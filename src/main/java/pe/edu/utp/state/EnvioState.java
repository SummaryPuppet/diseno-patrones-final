package pe.edu.utp.state;

import pe.edu.utp.dominio.Envio;

public interface EnvioState {
    void next(EnvioContexto envioContexto);
    String name();
    void aplicarReglaNegocio(Envio envio);
}
