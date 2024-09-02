package com.anschaucorp.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GenericData(@JsonAlias("codigo") String code,
                          @JsonAlias("nome") String description) {
}
