package com.example.Ra3FinalApplication.WebController;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String indexPage() {
        return "login";
    }

    @GetMapping("/control")
    public String controlAcceso(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String rol = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");

        if (rol.equals("ROLE_ADMIN")) {
            return "redirect:/admin/dashboard";
        } else if (rol.equals("ROLE_COORDINADOR")) {
            return "redirect:/coordinador/dashboard";
        }

        return "redirect:/login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/killSession")
    public String matarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}