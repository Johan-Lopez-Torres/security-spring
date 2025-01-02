package com.johan.securityspring;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookies")
public class CookiesController {

    // Endpoint para establecer una cookie
    @GetMapping("/set")
    public ResponseEntity<String> setCookie(HttpServletResponse response) {
        // Crear una nueva cookie
        Cookie cookie = new Cookie("user", "JohnDoe");
        cookie.setPath("/"); // La cookie será válida en todo el dominio
        cookie.setMaxAge(60 * 60); // La cookie durará 1 hora (en segundos)
        cookie.setHttpOnly(true); // No será accesible desde JavaScript
        cookie.setSecure(true); // Solo se enviará sobre HTTPS (si está habilitado en producción)

        // Agregar la cookie a la respuesta
        response.addCookie(cookie);

        return ResponseEntity.ok("Cookie establecida con éxito");
    }

    // Endpoint para leer una cookie
    @GetMapping("/get")
    public ResponseEntity<String> getCookie(@CookieValue(value = "user", defaultValue = "Desconocido") String user) {
        return ResponseEntity.ok("El valor de la cookie 'user' es: " + user);
    }
}
