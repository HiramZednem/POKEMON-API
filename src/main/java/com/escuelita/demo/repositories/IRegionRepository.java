package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRegionRepository extends JpaRepository<Region, Long> {

}
