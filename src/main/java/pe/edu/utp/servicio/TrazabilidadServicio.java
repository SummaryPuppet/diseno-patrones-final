package pe.edu.utp.servicio;

import java.util.List;

import pe.edu.utp.dominio.Trazabilidad;

public interface TrazabilidadServicio {
    Trazabilidad registrar(Trazabilidad t);

    List<Trazabilidad> listarPorEnvio(Long envioId);
}
