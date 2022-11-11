package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateTrainerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateTrainerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("trainer")
public class TrainerController {

    @Autowired
    private ITrainerService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> get (@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>( baseResponse, baseResponse.getHttpStatus() );
    }

    @GetMapping
    ResponseEntity<BaseResponse> getAll () {
        BaseResponse baseResponse = service.getAll();
        return new ResponseEntity<>( baseResponse, baseResponse.getHttpStatus() );
    }

    @PostMapping
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreateTrainerRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>( baseResponse, baseResponse.getHttpStatus() );
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable Long id, @RequestBody @Valid UpdateTrainerRequest request ) {
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
