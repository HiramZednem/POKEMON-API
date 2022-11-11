package com.escuelita.demo.services;

import com.escuelita.demo.controllers.dtos.requests.CreateRegionRequest;
import com.escuelita.demo.controllers.dtos.requests.UpdateRegionRequest;
import com.escuelita.demo.controllers.dtos.responses.BaseResponse;
import com.escuelita.demo.controllers.dtos.responses.CreateRegionResponse;
import com.escuelita.demo.entities.Region;
import com.escuelita.demo.repositories.IRegionRepository;
import com.escuelita.demo.services.interfaces.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("Region")
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionRepository repository;
    
    @Override
    public BaseResponse get(Long id) {
        Optional<Region> regionRepository = repository.findById(id);
        
        if ( regionRepository.isPresent() ) {
            return BaseResponse.builder()
                    .data( from(regionRepository.get()) )
                    .message("Region by id")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The region with the id " + id + " doesn't exist");
        
    }

    @Override
    public BaseResponse getAll() {
        List<CreateRegionResponse> responses = new ArrayList<>();
        List<Region> regions = repository.findAll();

        for (Region region : regions) {
            responses.add(from(region));
        }
        return BaseResponse.builder()
                .data(responses)
                .message("List of all Regions")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateRegionRequest request) {
        Region regionRepository = repository.save(from(request));

        return BaseResponse.builder()
                .data(from(regionRepository))
                .message("Region created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .build();
    }

    @Override
    public BaseResponse update(Long id, UpdateRegionRequest update) {
        Optional<Region> regionRepository = repository.findById(id);
        if (regionRepository.isPresent()) {
            Region region = regionRepository.get();
            CreateRegionResponse response = from( update(region, update) );
            return BaseResponse.builder()
                    .data( response )
                    .message("Region updated correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK)
                    .build();
        }
        throw new RuntimeException("The region with the id " + id + " doesn't exist");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Region from(CreateRegionRequest request) {
        Region response = new Region();
        response.setName(request.getName());
        return response;
    }

    private CreateRegionResponse from (Region request) {
        CreateRegionResponse response = new CreateRegionResponse();
        response.setId(request.getId());
        response.setName(request.getName());
        return response;
    }
    
    private Region update (Region region, UpdateRegionRequest update) {
        region.setName(update.getName());
        return repository.save(region);
    }
}
