package com.escuelita.demo.services.interfaces;

import com.escuelita.demo.controllers.dtos.requests.CreateTypeRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateTypeRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;

public interface ITypeService {
    BaseResponse get (Long id);

    BaseResponse getAll();

    BaseResponse create (CreateTypeRequest request);

    BaseResponse update (Long id, UpdateTypeRequest request);

    void delete (Long id);
}
