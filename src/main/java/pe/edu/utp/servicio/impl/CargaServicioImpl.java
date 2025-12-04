package pe.edu.utp.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Carga;
import pe.edu.utp.repository.CargaRepository;
import pe.edu.utp.servicio.CargaServicio;

@Service
@AllArgsConstructor
public class CargaServicioImpl implements CargaServicio {
    private final CargaRepository repo;

    @Override
    public Carga crearCarga(Carga carga) {
        // calcular totales
        double peso = carga.getItems().stream().mapToDouble(i -> i.getPesoKg() == null ? 0.0 : i.getPesoKg()).sum();
        double vol = carga.getItems().stream().mapToDouble(i -> i.getVolumenM3() == null ? 0.0 : i.getVolumenM3())
                .sum();
        carga.setPesoKg(peso);
        carga.setVolumenM3(vol);
        return repo.save(carga);
    }

    @Override
    public List<Carga> listarCargas() {
        return repo.findAll();
    }
}
