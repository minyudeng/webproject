package com.webproject.entity;

import lombok.Data;

import jakarta.persistence.Column;
@Data
public class Type {
    @Column(name = "type_id")
    private int typeId;
    private String type;
}
