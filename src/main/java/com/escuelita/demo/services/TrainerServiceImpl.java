package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateTrainerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateTrainerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateTrainerResponse;
import com.escuelita.demo.entities.Trainer;
import com.escuelita.demo.repositories.ITrainerRepository;
import com.escuelita.demo.services.interfaces.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements ITrainerService {

    @Autowired
    private ITrainerRepository repository;

    @Override
    public BaseResponse get(Long id) {
        Optional<Trainer> trainerRepository = repository.findById(id);

        if ( trainerRepository.isPresent() ) {
            CreateTrainerResponse response = from(trainerRepository.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Trainer by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The Trainer with the id: "+ id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreateTrainerResponse> responses = new ArrayList<>();
        List<Trainer> trainersRepository = repository.findAll();

        for (Trainer trainer: trainersRepository) {
            responses.add( from(trainer) );
        }
        return BaseResponse.builder()
                .data(responses)
                .message("List of all trainers")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateTrainerRequest request) {
        Trainer trainer = repository.save(from(request));
        CreateTrainerResponse response = from (trainer);
        return BaseResponse.builder()
                .data(response)
                .message("Trainer created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateTrainerRequest request) {
        Optional<Trainer> trainerRepository = repository.findById(id);

        if ( trainerRepository.isPresent() ) {
            Trainer trainer = trainerRepository.get();
            CreateTrainerResponse response = from(update(trainer, request));
            return BaseResponse.builder()
                    .data(response)
                    .message("Trainer Updated Correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        throw new RuntimeException("The Trainer with the id: "+ id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Trainer from (CreateTrainerRequest request) {
        Trainer response = new Trainer();
        response.setName(request.getName());
        return response;
    }

    private CreateTrainerResponse from (Trainer request) {
        CreateTrainerResponse response = new CreateTrainerResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        return response;
    }

    private Trainer update (Trainer trainer, UpdateTrainerRequest request) {
        trainer.setName(request.getName());
        return repository.save(trainer);
    }
}
