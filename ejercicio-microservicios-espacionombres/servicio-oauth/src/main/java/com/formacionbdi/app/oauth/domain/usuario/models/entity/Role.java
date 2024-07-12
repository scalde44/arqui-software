package com.formacionbdi.app.oauth.domain.usuario.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;
    private String name;
}
