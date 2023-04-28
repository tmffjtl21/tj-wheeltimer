package com.example.tjwheeltimer.resource;

import com.example.tjwheeltimer.service.WheelTimer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryResource {

    private WheelTimer wheelTimer;

    public DeliveryResource(WheelTimer wheelTimer) {
        this.wheelTimer = wheelTimer;
    }

    @GetMapping("/delivery")
    public ResponseEntity<String> delivery() throws Exception {
        wheelTimer.deliver();
        return ResponseEntity.ok().body("Success");
    }
}
