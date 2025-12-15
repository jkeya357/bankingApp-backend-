package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponseDto {

    private UUID id;
    private Map<String, FieldChange> changes;

}
