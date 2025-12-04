package pe.edu.utp.servicio.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Trazabilidad;
import pe.edu.utp.repository.TrazabilidadRepository;
import pe.edu.utp.servicio.TrazabilidadServicio;

@Service
@AllArgsConstructor
public class TrazabilidadServicioImpl implements TrazabilidadServicio {
    private final TrazabilidadRepository repo;

    @Override
    public Trazabilidad registrar(Trazabilidad t) {
        return repo.save(t);
    }

    @Override
    public List<Trazabilidad> listarPorEnvio(Long envioId) {
        return repo.findByEnvioIdOrderByFechaDesc(envioId);
    }
}
