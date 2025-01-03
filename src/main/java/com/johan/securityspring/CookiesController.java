package com.johan.securityspring;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookies")
public class CookiesController {

    // Endpoint para establecer una cookie de sesión (sessionId)
    @GetMapping("/set")
    public ResponseEntity<String> setSessionCookie(HttpServletResponse response, HttpSession session) {
        // Generar y obtener el sessionId automáticamente
        String sessionId = session.getId(); // Este es el sessionId gestionado por HttpSession

        // Crear una cookie con el sessionId
        Cookie cookie = new Cookie("sessionId", sessionId);
        cookie.setPath("/"); // La cookie será válida en todo el dominio
        cookie.setMaxAge(60 * 60); // La cookie durará 1 hora (en segundos)
        cookie.setHttpOnly(true); // No será accesible desde JavaScript
        cookie.setSecure(false); // Solo se enviará sobre HTTPS (si está habilitado en producción)
        session.invalidate();
        // Agregar la cookie a la respuesta
        response.addCookie(cookie);

        return ResponseEntity.ok("Cookie de sesión establecida con sessionId: " + sessionId);
    }

    // Endpoint para leer una cookie de sesión (sessionId)
    @GetMapping("/get")
    public ResponseEntity<String> getSessionCookie(@CookieValue(value = "sessionId", defaultValue = "No session found") String sessionId) {
        return ResponseEntity.ok("El valor de la cookie 'sessionId' es: " + sessionId);
    }
}
