package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateMoveRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateMoveRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface IMoveService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateMoveRequest request);

    BaseResponse update (Long id, UpdateMoveRequest request);

    void delete (Long id);
}
