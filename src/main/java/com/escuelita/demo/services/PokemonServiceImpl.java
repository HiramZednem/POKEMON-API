package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreatePokemonRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePokemonRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreatePokemonResponse;
import com.escuelita.demo.entities.Pokemon;
import com.escuelita.demo.repositories.IPokemonRepository;
import com.escuelita.demo.services.interfaces.IPokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PokemonServiceImpl implements IPokemonService {
    @Autowired
    private IPokemonRepository repository;

    @Override
    public BaseResponse get(Long id) {
        Optional<Pokemon> pokemonOptional = repository.findById(id);

        if( pokemonOptional.isPresent() ) {
            return BaseResponse.builder()
                    .data(from(pokemonOptional.get()))
                    .message("Pokemon by Id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The pokemon with the id " + id + " doesn't exist");
    }

    @Override
    public BaseResponse getAll() {
        List<CreatePokemonResponse> responses = new ArrayList<>();

        List<Pokemon> allPokemons = repository.findAll();

        for (Pokemon pokemon : allPokemons) {
            responses.add(from(pokemon));
        }
        return BaseResponse.builder()
                .data(responses)
                .message("List of all Pokemons")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreatePokemonRequest request) {
        Pokemon save = repository.save(from(request));
        return BaseResponse.builder()
                .data(from(save))
                .message("Pokemon created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdatePokemonRequest request) {
        Optional<Pokemon> pokemonOptional = repository.findById(id);
        if (pokemonOptional.isPresent()) {
            Pokemon pokemon = pokemonOptional.get();


            return BaseResponse.builder()
                    .data(from(update(pokemon,request)))
                    .message("Pokemon updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The pokemon with the id " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CreatePokemonResponse from (Pokemon request) {
        CreatePokemonResponse response = new CreatePokemonResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        response.setPicture(request.getPicture());
        return response;
    }

    private Pokemon from (CreatePokemonRequest request) {
        Pokemon response = new Pokemon();
        response.setName(request.getName());
        response.setPicture(request.getPicture());
        return response;
    }

    private Pokemon update (Pokemon pokemon, UpdatePokemonRequest request) {
        pokemon.setName(request.getName());
        pokemon.setPicture(request.getPicture());
        return repository.save(pokemon);
    }
}
