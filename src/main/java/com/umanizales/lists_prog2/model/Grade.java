package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase encargada de manejar el grado academico de cada niño
 * @author valentin
 * */
@Data
@AllArgsConstructor
public class Grade {
    @NotNull         //La identificación es obligatoria
    @NotEmpty       //No puede estar vacía
    private Integer grade;
}
