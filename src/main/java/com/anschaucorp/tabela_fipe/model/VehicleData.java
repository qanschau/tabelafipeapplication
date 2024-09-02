package com.anschaucorp.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleData(@JsonAlias("TipoVeiculo") Integer vehicleType,
                          @JsonAlias("Valor") String price,
                          @JsonAlias("Marca") String manufacturer,
                          @JsonAlias("Modelo") String model,
                          @JsonAlias("AnoModelo") Integer year,
                          @JsonAlias("Combustivel") String fuelType,
                          @JsonAlias("CodigoFipe") String fipeCode,
                          @JsonAlias("MesReferencia") String month,
                          @JsonAlias("SiglaCombustivel") String fuelCode) {

    @Override
    public String toString() {
        return "vehicleType=" + vehicleType +
                ", price='" + price + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
