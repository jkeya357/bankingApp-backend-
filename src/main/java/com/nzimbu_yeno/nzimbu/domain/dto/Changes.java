package com.nzimbu_yeno.nzimbu.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Changes {

    private Map<String, FieldChange> updates = new HashMap<>();

    public void add(String field, Object oldValue, Object newValue) {
        updates.put(field, new FieldChange(oldValue, newValue, LocalDateTime.now()));
    }
}
