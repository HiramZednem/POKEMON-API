package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Trainer;
import com.escuelita.demo.entities.projections.TrainerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainer, Long> {
    @Query(value = "select trainers.* from players inner join trainers on players.trainer_id = trainers.id where players.id = :id",nativeQuery = true)
    TrainerProjection getTrainerByPlayerId(Long id);
}
