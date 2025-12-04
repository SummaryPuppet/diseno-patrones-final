package pe.edu.utp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.utp.dominio.Envio;
import pe.edu.utp.dominio.EnvioTipo;
import pe.edu.utp.facade.EnvioFacade;
import pe.edu.utp.repository.EnvioRepository;
import pe.edu.utp.servicio.EnvioServicio;

@Controller
@RequestMapping("/envios")
public class EnvioViewController {
    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private pe.edu.utp.repository.RutaRepository rutaRepository;
    @Autowired
    private EnvioFacade envioFacade;
    @Autowired
    private EnvioServicio envioServicio;

    @GetMapping
    public String lista(Model model,
            @org.springframework.web.bind.annotation.RequestParam(value = "page", defaultValue = "0") int page,
            @org.springframework.web.bind.annotation.RequestParam(value = "size", defaultValue = "10") int size) {
        org.springframework.data.domain.PageRequest pageRequest = org.springframework.data.domain.PageRequest.of(page,
                size, org.springframework.data.domain.Sort.by("id").descending());
        org.springframework.data.domain.Page<pe.edu.utp.dominio.Envio> enviosPage = envioRepository
                .findAll(pageRequest);

        model.addAttribute("envios", enviosPage.getContent());
        model.addAttribute("page", enviosPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "envios/list";
    }

    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("envio", new Envio());
        model.addAttribute("tipos", EnvioTipo.values());
        model.addAttribute("rutas", rutaRepository.findAll());
        return "envios/form";
    }

    @PostMapping
    public String crear(@ModelAttribute Envio envio) {
        envioFacade.crearYProgramarEnvio(envio);
        return "redirect:/envios";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttrs) {
        java.util.Optional<pe.edu.utp.dominio.Envio> opt = envioRepository.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("envio", opt.get());
            return "envios/detail";
        } else {
            redirectAttrs.addFlashAttribute("error", "Envío no encontrado: " + id);
            return "redirect:/envios";
        }
    }

    @PostMapping("/{id}/avance")
    public String avance(@PathVariable Long id,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttrs) {
        pe.edu.utp.dominio.Envio actualizado = envioServicio.avanceEstado(id);
        redirectAttrs.addFlashAttribute("message", "Estado actualizado a: " + actualizado.getStatus());
        return "redirect:/envios";
    }
}
