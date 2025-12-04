package pe.edu.utp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.dominio.Trazabilidad;
import pe.edu.utp.repository.TrazabilidadRepository;

import java.util.List;

@RestController
@RequestMapping("/api/trazabilidad")
@AllArgsConstructor
public class TrazabilidadApiController {
    private final TrazabilidadRepository repo;

    @GetMapping
    public ResponseEntity<List<Trazabilidad>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/envio/{envioId}")
    public ResponseEntity<List<Trazabilidad>> porEnvio(@PathVariable Long envioId) {
        return ResponseEntity.ok(repo.findByEnvioIdOrderByFechaDesc(envioId));
    }

    @PostMapping
    public ResponseEntity<Trazabilidad> crear(@RequestBody Trazabilidad t) {
        return ResponseEntity.ok(repo.save(t));
    }
}
