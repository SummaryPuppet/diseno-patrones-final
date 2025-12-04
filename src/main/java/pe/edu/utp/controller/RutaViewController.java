package pe.edu.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Ruta;
import pe.edu.utp.servicio.RutaServicio;

@Controller
@RequestMapping("/rutas")
@AllArgsConstructor
public class RutaViewController {
    private final RutaServicio rutaServicio;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("rutas", rutaServicio.listarRutas());
        return "rutas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("ruta", new Ruta());
        return "rutas/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Ruta ruta) {
        rutaServicio.crearRuta(ruta);
        return "redirect:/rutas";
    }

    @GetMapping("/calcular")
    public String calcular(@RequestParam String origen, @RequestParam String destino,
            @RequestParam(defaultValue = "CORTA") String criterio, Model model) {
        Ruta opt = rutaServicio.calcularRutaOptima(origen, destino, criterio);
        model.addAttribute("rutaOptima", opt);
        return "rutas/result";
    }
}
