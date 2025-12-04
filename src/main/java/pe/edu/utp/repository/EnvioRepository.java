package pe.edu.utp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.dominio.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    List<Envio> findByOrigenContainingIgnoreCase(String origen);

    List<Envio> findByDestinoContainingIgnoreCase(String destino);
}
