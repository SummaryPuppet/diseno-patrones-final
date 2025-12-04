package pe.edu.utp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUser")
    public String currentUser(HttpSession session) {
        if (session == null)
            return null;
        Object u = session.getAttribute("user");
        return u != null ? u.toString() : null;
    }

}
