package com.unifranz.programaciontres.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResumenDto {
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
}
