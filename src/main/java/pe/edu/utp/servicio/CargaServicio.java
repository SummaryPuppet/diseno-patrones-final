package pe.edu.utp.servicio;

import pe.edu.utp.dominio.Carga;

import java.util.List;

public interface CargaServicio {
    Carga crearCarga(Carga carga);

    List<Carga> listarCargas();
}
