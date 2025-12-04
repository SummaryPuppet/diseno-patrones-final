package pe.edu.utp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("title", "Iniciar sesión");
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            HttpServletRequest request,
            RedirectAttributes redirectAttrs) {
        // Credenciales simples para demo. Cambia por verificación real si necesitas.
        if ("admin".equals(username) && "password1".equals(password)) {
            session.setAttribute("user", username);
            // Redirect to home or referer
            String referer = request.getHeader("Referer");
            return "redirect:/";
        }

        redirectAttrs.addAttribute("error", "Credenciales inválidas");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logoutPost(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
