package com.umanizales.lists_prog2.controller.dto;

import com.umanizales.lists_prog2.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenderGradesRHDTO {
    private Gender gender;
    private GradeRHDTO[] gradesRHDTO;
}
