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
import pe.edu.utp.dominio.Carga;
import pe.edu.utp.repository.CargaRepository;

@RestController
@RequestMapping("/api/cargas")
@AllArgsConstructor
public class CargaApiController {
    private final CargaRepository repo;

    @GetMapping
    public ResponseEntity<List<Carga>> listar() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carga> obtener(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carga> crear(@RequestBody Carga carga) {
        // calcular totales si hay items
        double peso = carga.getItems() == null ? 0.0
                : carga.getItems().stream().mapToDouble(i -> i.getPesoKg() == null ? 0.0 : i.getPesoKg()).sum();
        double vol = carga.getItems() == null ? 0.0
                : carga.getItems().stream().mapToDouble(i -> i.getVolumenM3() == null ? 0.0 : i.getVolumenM3()).sum();
        carga.setPesoKg(peso);
        carga.setVolumenM3(vol);
        return ResponseEntity.ok(repo.save(carga));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carga> actualizar(@PathVariable Long id, @RequestBody Carga cambios) {
        return repo.findById(id).map(c -> {
            c.setDescripcion(cambios.getDescripcion());
            c.setItems(cambios.getItems());
            c.setPesoKg(cambios.getPesoKg());
            c.setVolumenM3(cambios.getVolumenM3());
            return ResponseEntity.ok(repo.save(c));
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
