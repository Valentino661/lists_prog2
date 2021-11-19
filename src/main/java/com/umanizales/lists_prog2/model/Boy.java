package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Clase para almacenar la información referente a un niño
 * Maneja campos obligatorios para (identificación, edad, nombre, género ...
 * @autor Valentin
 * @version 1.0 - 30-oct-2021
 *
 */

@Data
@AllArgsConstructor
public class Boy {
    @NotNull        //La identificación obligatoria
    @NotEmpty       //No puede estar vacía
    @Size(min = 2)  //Mínimo de caracteres
    private String identification;   // Atributo para diferenciar los niños
    @NonNull
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;             // Atributo para saber el nombre de cada niño
    @Positive
    private byte age;               // Atributo para saber la edad de cada niño
    @NotNull
    private Gender gender;          // Atributo para saber el género de cada niño
    @Valid           // Mira si existe el dato mandado
    @NotNull
    private Location location;      // Atributo para saber la localización (municipio) de cada niño
    @NotNull        //La identificación obligatoria
    @NotEmpty       //No puede estar vacía
    private byte grade;            // Atributo que representa el grado en el que está el niño
    @NotNull
    private boolean orphan;
    @NotNull
    private String rh;

}
