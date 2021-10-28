package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Boy {
    @NotNull        //La identificacion es obligatoria
    @NotEmpty       //No puede estar vacia
    @Size(min = 2)  //Minimo de caracteres
    private String identification;
    @NonNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;
    @Positive
    private byte age;
    @NotNull
    private Gender gender;
    @Valid
    @NotNull
    private Location location;
}
