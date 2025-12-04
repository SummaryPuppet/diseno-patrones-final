package pe.edu.utp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.utp.repository.EnvioRepository;

@Controller
@RequestMapping("/envios")
@AllArgsConstructor
public class EnvioSearchController {
    private final EnvioRepository envioRepository;

    @GetMapping("/buscar/{id}")
    public String buscarPorId(@PathVariable String id, Model model) {
        List<Object> resultados = new ArrayList<>();
        try {
            Long lid = Long.parseLong(id);
            envioRepository.findById(lid).ifPresent(envio -> resultados.add(envio));
        } catch (NumberFormatException e) {
            // no es un id numérico -> no resultados
        }
        model.addAttribute("resultados", resultados);
        model.addAttribute("query", id);
        return "envios/search";
    }

    @GetMapping("/buscar")
    public String buscar(@org.springframework.web.bind.annotation.RequestParam(name = "q", required = false) String q,
            Model model) {
        List<Object> resultados = new ArrayList<>();
        if (q != null && !q.isBlank()) {
            String trimmed = q.trim();
            // intentar parsear como id
            try {
                Long lid = Long.parseLong(trimmed);
                envioRepository.findById(lid).ifPresent(envio -> resultados.add(envio));
            } catch (NumberFormatException e) {
                // no es id numérico, buscar por origen/destino
                resultados.addAll(envioRepository.findByOrigenContainingIgnoreCase(trimmed));
                resultados.addAll(envioRepository.findByDestinoContainingIgnoreCase(trimmed));
            }
        }
        model.addAttribute("resultados", resultados);
        model.addAttribute("query", q == null ? "" : q);
        return "envios/search";
    }
}
