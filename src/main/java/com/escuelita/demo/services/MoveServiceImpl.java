package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateMoveRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateMoveRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateMoveResponse;
import com.escuelita.demo.entities.Move;
import com.escuelita.demo.repositories.IMoveRepository;
import com.escuelita.demo.services.interfaces.IMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoveServiceImpl implements IMoveService {
    @Autowired
    private IMoveRepository repository;

    @Override
    public BaseResponse get(Long id) {
        Optional<Move> moveOptional = repository.findById(id);

        if (moveOptional.isPresent() ) {
            return BaseResponse.builder()
                    .data(from(moveOptional.get()))
                    .message("Move by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The move with the id " + id + " doesn't exit");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateMoveResponse> responses = new ArrayList<>();
        List<Move> allMoves = repository.findAll();

        for (Move move : allMoves ) {
            responses.add(from(move));
        }
        return BaseResponse.builder()
                .data(responses)
                .message("List of all moves")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateMoveRequest request) {
        Move moveRepository = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(moveRepository))
                .message("Move created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateMoveRequest request) {
        Optional<Move> moveOptional = repository.findById(id);

        if (moveOptional.isPresent()) {
            Move move = moveOptional.get();
            return BaseResponse.builder()
                    .data(from(update(move, request)))
                    .message("Move updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Move with the id " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CreateMoveResponse from (Move request) {
        CreateMoveResponse response = new CreateMoveResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        return response;
    }

    private Move from (CreateMoveRequest request) {
        Move response = new Move();
        response.setName(request.getName());
        return response;
    }

    private Move update (Move move, UpdateMoveRequest request) {
        move.setName(request.getName());
        return repository.save(move);
    }
}
