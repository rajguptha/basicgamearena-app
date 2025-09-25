package com.example.basicgamearena.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MetaController {

    @Value("${app.name:basic-game-arena}") private String appName;
    @Value("${app.version:0.1.0-SNAPSHOT}") private String appVersion;
    @Value("${app.commit:unknown}") private String commit;

    @GetMapping("/hello")
    public ResponseEntity<Map<String, Object>> hello() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "Hello from BasicGameArena!");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/metadata")
    public ResponseEntity<Map<String, Object>> metadata() {
        Map<String, Object> resp = new HashMap<>();
        resp.put("name", appName);
        resp.put("version", appVersion);
        resp.put("commit", commit);
        resp.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(resp);
    }
}
