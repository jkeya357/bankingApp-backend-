package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldChange {

    private Object oldValue;
    private Object newValue;
    private LocalDateTime updatedAt;
}
