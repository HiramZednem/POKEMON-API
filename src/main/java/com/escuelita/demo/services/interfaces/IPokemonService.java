package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreatePokemonRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePokemonRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface IPokemonService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreatePokemonRequest request);

    BaseResponse update (Long id, UpdatePokemonRequest request);

    void delete (Long id);
}
