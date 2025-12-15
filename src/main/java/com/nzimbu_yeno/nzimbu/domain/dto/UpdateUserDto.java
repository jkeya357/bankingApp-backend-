package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private Map<String, FieldChange> updatedField;
}
