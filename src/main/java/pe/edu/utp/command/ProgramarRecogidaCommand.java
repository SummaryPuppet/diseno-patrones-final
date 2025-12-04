package pe.edu.utp.command;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Envio;
import pe.edu.utp.servicio.EnvioServicio;

@AllArgsConstructor
public class ProgramarRecogidaCommand implements Command {
    private final EnvioServicio envioService;
    private final Envio envio;


    @Override
    public void ejecutar() {
        envioService.programarRecogida(envio);
    }
    @Override
    public void deshacer() {
        envioService.cancelarRecogida(envio);
    }
}
