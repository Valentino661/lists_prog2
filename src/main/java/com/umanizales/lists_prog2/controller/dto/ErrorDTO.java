package com.umanizales.lists_prog2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase
 * */
@Data
@AllArgsConstructor
public class ErrorDTO {
    private int code;
    private String meesage;
}
