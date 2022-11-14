package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreatePokemonRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePokemonRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("pokemon")
public class PokemonController {

    @Autowired
    private IPokemonService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> get (@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    ResponseEntity<BaseResponse> getAll () {
        BaseResponse baseResponse = service.getAll();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreatePokemonRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable Long id,@RequestBody @Valid UpdatePokemonRequest request) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
