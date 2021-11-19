package com.umanizales.lists_prog2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Clase implementada para manejar países, departamentos y ciudades en un solo objeto
 * Ejemplo: code:169 desccription:Colombia
 * Ejemplo2: code:16917 description:Caldas
 * Ejemplo3: code:16917001 description: Manizales
 * @author Valentin
 */

@Data
@AllArgsConstructor
public class Location {
    @NotNull         //La identificación es obligatoria
    @NotEmpty       //No puede estar vacía
    private String code;           // Atributo que define el código del municipio, pais ...
    @NotNull
    @NotEmpty
    private String description;    // Atributo que nos dice el nombre de municipio, pais ...

}
