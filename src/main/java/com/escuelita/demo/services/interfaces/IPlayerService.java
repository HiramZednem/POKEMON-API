package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;

import java.util.List;

public interface IPlayerService {
    CreatePlayerResponse get (Long id);

    List<CreatePlayerResponse> getAll ();

    CreatePlayerResponse create (CreatePlayerRequest request);

    CreatePlayerResponse update (Long id, UpdatePlayerRequest request);

    void delete (Long id);

}
