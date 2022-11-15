package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreatePlayerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdatePlayerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreatePlayerResponse;

import java.util.List;

public interface IPlayerService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreatePlayerRequest request);

    BaseResponse update (Long id, UpdatePlayerRequest request);

    void delete (Long id);

    boolean existById(Long id);



}
