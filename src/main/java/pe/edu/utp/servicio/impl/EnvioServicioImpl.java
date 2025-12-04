package pe.edu.utp.servicio.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Envio;
import pe.edu.utp.evento.EnvioStateCambioEvento;
import pe.edu.utp.repository.EnvioRepository;
import pe.edu.utp.servicio.EnvioServicio;
import pe.edu.utp.state.EnvioContexto;

@Service
@AllArgsConstructor
public class EnvioServicioImpl implements EnvioServicio {
    @Autowired
    private final EnvioRepository repo;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Envio crearEnvio(Envio envio) {
        Envio nuevoEnvio = repo.save(envio);
        publisher.publishEvent(new EnvioStateCambioEvento(this, nuevoEnvio.getId(), nuevoEnvio.getStatus()));
        return nuevoEnvio;
    }

    @Override
    public Optional<Envio> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Envio avanceEstado(Long id) {
        Envio envio = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Envio no encontrado: " + id));
        EnvioContexto ctx = new EnvioContexto(envio);
        ctx.next(envio);

        repo.save(envio);
        publisher.publishEvent(new EnvioStateCambioEvento(this, envio.getId(), envio.getStatus()));
        return envio;
    }

    @Override
    public void programarRecogida(Envio envio) {
        System.out.println("Recogida programada para el envío ID: " + envio.getId());
    }

    @Override
    public void cancelarRecogida(Envio envio) {
        System.out.println("Recogida cancelada para el envío ID: " + envio.getId());
    }
}
