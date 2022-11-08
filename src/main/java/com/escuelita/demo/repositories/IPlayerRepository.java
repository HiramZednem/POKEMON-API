package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlayerRepository extends JpaRepository<Player,Long> {

}
