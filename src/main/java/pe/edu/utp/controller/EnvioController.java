package pe.edu.utp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Envio;
import pe.edu.utp.facade.EnvioFacade;
import pe.edu.utp.repository.EnvioRepository;
import pe.edu.utp.servicio.EnvioServicio;

@RequestMapping("/api/envios")
@RestController
@AllArgsConstructor
public class EnvioController {
    private final EnvioFacade envioFacade;
    private final EnvioServicio envioServicio;
    private final EnvioRepository envioRepository;

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioFacade.crearYProgramarEnvio(envio);
        return ResponseEntity.ok(nuevoEnvio);
    }

    @GetMapping
    public ResponseEntity<java.util.List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvio(@PathVariable Long id) {
        return envioServicio.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/avance")
    public ResponseEntity<Envio> avanceEstado(@PathVariable Long id) {
        Envio envioActualizado = envioServicio.avanceEstado(id);
        return ResponseEntity.ok(envioActualizado);
    }

}
