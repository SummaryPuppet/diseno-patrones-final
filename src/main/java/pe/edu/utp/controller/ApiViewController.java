package pe.edu.utp.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiViewController {

    @GetMapping("/api")
    public String apiIndex(Model model) {
        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("/api/envios", "Envíos API (listar, crear, obtener, avanzar estado)");
        endpoints.put("/api/rutas", "Rutas API (listar, crear)");
        endpoints.put("/api/cargas", "Cargas API (gestionar cargas)");
        endpoints.put("/api/trazabilidad", "Trazabilidad API (historial de envíos)");

        model.addAttribute("endpoints", endpoints);
        model.addAttribute("title", "API - EnviosApp");
        return "api/index";
    }
}
