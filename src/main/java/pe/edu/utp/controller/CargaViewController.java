package pe.edu.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import pe.edu.utp.dominio.Carga;
import pe.edu.utp.dominio.CargaItem;
import pe.edu.utp.servicio.CargaServicio;

@Controller
@RequestMapping("/cargas")
@AllArgsConstructor
public class CargaViewController {
    private final CargaServicio cargaServicio;

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("cargas", cargaServicio.listarCargas());
        return "cargas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Carga c = new Carga();
        c.getItems().add(new CargaItem());
        model.addAttribute("carga", c);
        return "cargas/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Carga carga) {
        cargaServicio.crearCarga(carga);
        return "redirect:/cargas";
    }
}
