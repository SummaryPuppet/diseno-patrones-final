package pe.edu.utp.state;

import pe.edu.utp.dominio.Envio;

public class EnvioContexto {
    private EnvioState state;

    public EnvioContexto() {
        this.state = new CreadoState();
    }

    /**
     * Inicializa el contexto en el estado correspondiente según el valor actual
     * de `envio.getStatus()`. Si el estado no se reconoce, se asume `CREADO`.
     */
    public EnvioContexto(Envio envio) {
        if (envio == null || envio.getStatus() == null) {
            this.state = new CreadoState();
            return;
        }

        String s = envio.getStatus().trim().toUpperCase();
        switch (s) {
            case "CREADO":
                this.state = new CreadoState();
                break;
            case "EN CAMINO":
                this.state = new EnCaminoState();
                break;
            case "ENCAMINO":
                this.state = new EnCaminoState();
                break;
            case "ENTREGADO":
                this.state = new EntregadoState();
                break;
            default:
                this.state = new CreadoState();
                break;
        }
    }

    public void setState(EnvioState state) {
        this.state = state;
    }

    public String actual() {
        return state.name();
    }

    public void next(Envio envio) {
        state.next(this);
        this.state.aplicarReglaNegocio(envio);
    }
}
