package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateTypeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateTypeRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateTypeResponse;
import com.escuelita.demo.entities.Type;
import com.escuelita.demo.repositories.ITypeRepository;
import com.escuelita.demo.services.interfaces.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeServiceImpl implements ITypeService {
    @Autowired
    private ITypeRepository repository;

    @Override
    public BaseResponse get(Long id) {
        Optional<Type> typeOptional = repository.findById(id);
        if (typeOptional.isPresent()) {
            return BaseResponse.builder()
                    .data(from(typeOptional.get()))
                    .message("Type by Id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Type with the id " + id + "doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateTypeResponse> responses = new ArrayList<>();
        List<Type> allTypes = repository.findAll();

        for (Type type : allTypes) {
            responses.add(from(type));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all Types")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateTypeRequest request) {
        Type typeRepository = repository.save(from(request));

        return BaseResponse.builder()
                .data(from(typeRepository))
                .message("Type created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateTypeRequest request) {
        Optional<Type> typeOptional = repository.findById(id);

        if (typeOptional.isPresent()) {
            Type type = typeOptional.get();
            return BaseResponse.builder()
                    .data(from(update(type,request)))
                    .message("Type updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Type with the id " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CreateTypeResponse from (Type request) {
        CreateTypeResponse response = new CreateTypeResponse();
        response.setId(request.getId());
        response.setType(request.getType());
        return response;
    }

    private Type from (CreateTypeRequest request) {
        Type response = new Type();
        response.setType(request.getType());
        return response;
    }

    private Type update (Type type, UpdateTypeRequest request) {
        type.setType(request.getType());
        return repository.save(type);
    }
}
