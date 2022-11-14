package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMoveRepository extends JpaRepository <Move,Long> {
}
