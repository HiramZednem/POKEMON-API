package com.escuelita.demo.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreatePokemonRequest {
    @NotNull @NotBlank
    private String name;

    @NotNull @NotBlank
    private String picture;
}
