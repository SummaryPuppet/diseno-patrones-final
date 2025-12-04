package pe.edu.utp.factory;

import pe.edu.utp.dominio.Envio;
import pe.edu.utp.dominio.EnvioTipo;

public class EnvioFactory {
    public static Envio create(EnvioTipo tipo, String origen, String destino) {
        Envio envio = new Envio();
        envio.setEnvioTipo(tipo);
        envio.setOrigen(origen);
        envio.setDestino(destino);
        envio.setStatus("CREADO");
        return envio;
    }
}
