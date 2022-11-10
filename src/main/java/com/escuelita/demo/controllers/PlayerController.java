package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;
import com.escuelita.demo.services.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("player")
public class PlayerController {
    @Autowired
    @Qualifier("player")
    private IPlayerService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> get (@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    ResponseEntity<BaseResponse> getAll ( ) {
        BaseResponse baseResponse = service.getAll();
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreatePlayerRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable @Valid Long id, @RequestBody UpdatePlayerRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<BaseResponse>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
