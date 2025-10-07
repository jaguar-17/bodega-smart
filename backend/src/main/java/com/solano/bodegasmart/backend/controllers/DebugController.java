package com.solano.bodegasmart.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    @GetMapping("/timezone")
    public Map<String, String> getTimezoneInfo() {
        Map<String, String> info = new HashMap<>();

        // Zona horaria por defecto de la JVM
        TimeZone defaultTimeZone = TimeZone.getDefault();
        info.put("zonaHorariaServidor", defaultTimeZone.getID());
        info.put("offset", defaultTimeZone.getDisplayName());

        // ZoneId del sistema
        ZoneId zoneId = ZoneId.systemDefault();
        info.put("zoneIdSistema", zoneId.toString());

        // Hora actual del servidor (como se guardará en la BD)
        LocalDateTime localNow = LocalDateTime.now();
        info.put("horaLocalDateTime", localNow.toString());

        // Hora actual con zona horaria
        ZonedDateTime zonedNow = ZonedDateTime.now();
        info.put("horaConZona", zonedNow.toString());

        // Hora en zona horaria de Perú
        ZonedDateTime peruTime = ZonedDateTime.now(ZoneId.of("America/Lima"));
        info.put("horaPerú", peruTime.toString());

        // Diferencia
        info.put("diferencia", "Si son diferentes, necesitas configurar la zona horaria");

        return info;
    }
}

