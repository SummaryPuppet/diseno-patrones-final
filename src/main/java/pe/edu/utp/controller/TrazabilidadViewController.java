package pe.edu.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.utp.servicio.TrazabilidadServicio;

@Controller
@RequestMapping("/trazabilidad")
@AllArgsConstructor
public class TrazabilidadViewController {
    private final TrazabilidadServicio trazabilidadServicio;

    @GetMapping("/envio/{id}")
    public String porEnvio(@PathVariable Long id, Model model) {
        model.addAttribute("trazas", trazabilidadServicio.listarPorEnvio(id));
        model.addAttribute("envioId", id);
        return "trazabilidad/list";
    }
}
