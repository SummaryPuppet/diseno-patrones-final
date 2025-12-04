package pe.edu.utp.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Ruta;
import pe.edu.utp.repository.RutaRepository;
import pe.edu.utp.servicio.RutaServicio;

@Service
@AllArgsConstructor
public class RutaServicioImpl implements RutaServicio {
    private final RutaRepository repo;

    @Override
    public Ruta crearRuta(Ruta ruta) {
        return repo.save(ruta);
    }

    @Override
    public List<Ruta> listarRutas() {
        return repo.findAll();
    }

    @Override
    public Ruta calcularRutaOptima(String origen, String destino, String criterio) {
        // Simple ejemplo: strategy por criterio
        Ruta r = new Ruta();
        r.setOrigen(origen);
        r.setDestino(destino);
        if ("RAPIDA".equalsIgnoreCase(criterio)) {
            r.setDistanciaKm(120.0);
            r.setTiempoEstimadoHoras(1.5);
            r.setTipo("RAPIDA");
        } else {
            r.setDistanciaKm(100.0);
            r.setTiempoEstimadoHoras(2.0);
            r.setTipo("CORTA");
        }
        return r;
    }
}
