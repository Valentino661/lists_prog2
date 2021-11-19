package com.umanizales.lists_prog2.controller.dto;

import com.umanizales.lists_prog2.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradesByLocationDTO {
    private Location location;
    private List<GendersByGradeDTO> gendersByGradeDTOS;

}
