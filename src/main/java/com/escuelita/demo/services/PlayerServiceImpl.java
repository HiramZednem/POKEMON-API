package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;
import com.escuelita.demo.entities.Player;
import com.escuelita.demo.repositories.IPlayerRepository;
import com.escuelita.demo.services.interfaces.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("player")
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerRepository repository;

    @Override
    public CreatePlayerResponse get(Long id) {
        Optional<Player> playerRepository = repository.findById(id);

        if (playerRepository.isPresent()) {
            return  from(playerRepository.get());
        }
        throw new RuntimeException("The player with id: "+ id + " doesn't exist");

    }

    @Override
    public List<CreatePlayerResponse> getAll() {
        List<CreatePlayerResponse> responses = new ArrayList<>();
        List<Player> repositoryAll = repository.findAll();

        for (Player player : repositoryAll) {
            responses.add(from(player));
        }

        return responses;
    }

    @Override
    public CreatePlayerResponse create(CreatePlayerRequest request) {
        Player playerRepository = repository.save(from(request));
        return from(playerRepository);
    }

    @Override
    public CreatePlayerResponse update(Long id, UpdatePlayerRequest request) {
        //This method is like this because we are trying to don't create more instances and save memory
        Optional<Player> playerRepository = repository.findById(id);

        if (playerRepository.isPresent()) {
            Player player = playerRepository.get();
            return from( update(player, request) );
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
