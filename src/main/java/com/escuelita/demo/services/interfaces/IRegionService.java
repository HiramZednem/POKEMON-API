package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateRegionRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateRegionRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface IRegionService {
    BaseResponse get (Long id);

    BaseResponse getAll ();

    BaseResponse create (CreateRegionRequest request);

    BaseResponse update (Long id, UpdateRegionRequest update);

    void delete (Long id);
}
