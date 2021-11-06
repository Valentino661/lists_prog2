package com.umanizales.lists_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Clase que nos estandariza las respuestas de todo el servicio
 * */
@Data
@AllArgsConstructor
public class ResponseDTO {
    private String message;
    private Object data;
    private List<ErrorDTO> errors;
}
