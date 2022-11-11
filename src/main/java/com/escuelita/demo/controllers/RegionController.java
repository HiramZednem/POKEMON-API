package com.escuelita.demo.controllers;

import com.escuelita.demo.controllers.dtos.requests.CreateRegionRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateRegionRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.services.interfaces.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("region")
public class RegionController {
    @Autowired
    @Qualifier("region")
    private IRegionService service;

    @GetMapping("{id}")
    ResponseEntity<BaseResponse> get (@PathVariable Long id) {
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    ResponseEntity<BaseResponse> getAll () {
        BaseResponse baseResponse = service.getAll();
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PostMapping
    ResponseEntity<BaseResponse> create (@RequestBody @Valid CreateRegionRequest request) {
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    ResponseEntity<BaseResponse> update (@PathVariable Long id, @RequestBody @Valid UpdateRegionRequest update) {
        BaseResponse baseResponse = service.update(id, update);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    void delete (@PathVariable Long id) {
        service.delete(id);
    }
}
