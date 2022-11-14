package com.escuelita.demo.repositories;

import com.escuelita.demo.entities.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPokemonRepository extends JpaRepository<Pokemon, Long> {
}
