package pe.edu.utp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.utp.dominio.Trazabilidad;

public interface TrazabilidadRepository extends JpaRepository<Trazabilidad, Long> {
    List<Trazabilidad> findByEnvioIdOrderByFechaDesc(Long envioId);
}
