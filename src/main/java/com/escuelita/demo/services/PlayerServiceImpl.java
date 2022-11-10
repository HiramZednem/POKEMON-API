package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;
import com.escuelita.demo.entities.Player;
import com.escuelita.demo.repositories.IPlayerRepository;
import com.escuelita.demo.services.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("player")
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository repository;

    @Override
    public BaseResponse get(Long id) {
        Optional<Player> playerRepository = repository.findById(id);

        if (playerRepository.isPresent()) {
            CreatePlayerResponse response = from(playerRepository.get());
            return BaseResponse.builder()
                    .data(response)
                    .message("Player by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The player with id: "+ id + " doesn't exist");

    }

    @Override
    public BaseResponse getAll() {
        List<CreatePlayerResponse> responses = new ArrayList<>();
        List<Player> repositoryAll = repository.findAll();

        for (Player player : repositoryAll) {
            responses.add(from(player));
        }

        return BaseResponse.builder()
                .data(responses)
                .message("List of all players")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreatePlayerRequest request) {
        Player playerRepository = repository.save(from(request));
        CreatePlayerResponse response = from(playerRepository);
        return BaseResponse.builder()
                .data(response)
                .message("Player Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdatePlayerRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<Player> playerRepository = repository.findById(id);

        if (playerRepository.isPresent()) {
            Player player = playerRepository.get();
            CreatePlayerResponse response = from(update(player, request));

            return BaseResponse.builder()
                    .data(response)
                    .message("User updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The player with id: "+ id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CreatePlayerResponse from (Player request) {
        CreatePlayerResponse response = new CreatePlayerResponse();
        response.setId(request.getId());
        response.setUser(request.getUser());
        return response;
    }

    private Player from (CreatePlayerRequest request) {
        Player response = new Player();
        response.setUser(request.getUser());
        response.setPassword(request.getPassword());
        return response;
    }

    private Player update (Player player, UpdatePlayerRequest request) {
        player.setUser(request.getUser());
        player.setPassword(request.getPassword());
        return repository.save(player);
    }
}
