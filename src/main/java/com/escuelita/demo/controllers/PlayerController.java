package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;
import com.escuelita.demo.services.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {
    @Autowired
    @Qualifier("player")
    private IPlayerService service;

    @GetMapping("{id}")
    CreatePlayerResponse get (@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    List<CreatePlayerResponse> getAll ( ) {
        return service.getAll();
    }

    @PostMapping
    CreatePlayerResponse create (@RequestBody CreatePlayerRequest request) {
        return service.create(request);
    }

    @PutMapping("{id}")
    CreatePlayerResponse update (@PathVariable Long id, @RequestBody UpdatePlayerRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
