package pe.edu.utp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Ruta;
import pe.edu.utp.repository.RutaRepository;

@RestController
@RequestMapping("/api/rutas")
@AllArgsConstructor
public class RutaApiController {
    private final RutaRepository repo;

    @GetMapping
    public ResponseEntity<List<Ruta>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtener(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(repo.save(ruta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ruta> actualizar(@PathVariable Long id, @RequestBody Ruta cambios) {
        return repo.findById(id).map(r -> {
            r.setOrigen(cambios.getOrigen());
            r.setDestino(cambios.getDestino());
            r.setDistanciaKm(cambios.getDistanciaKm());
            r.setTiempoEstimadoHoras(cambios.getTiempoEstimadoHoras());
            r.setTipo(cambios.getTipo());
            return ResponseEntity.ok(repo.save(r));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
