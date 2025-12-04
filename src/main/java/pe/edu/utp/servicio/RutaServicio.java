package pe.edu.utp.servicio;

import java.util.List;

import pe.edu.utp.dominio.Ruta;

public interface RutaServicio {
    Ruta crearRuta(Ruta ruta);

    List<Ruta> listarRutas();

    Ruta calcularRutaOptima(String origen, String destino, String criterio);
}
