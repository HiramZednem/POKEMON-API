package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateTrainerRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateTrainerRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface ITrainerService {

    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateTrainerRequest request);

    BaseResponse update (Long id, UpdateTrainerRequest request);

    void delete (Long id);
}
