package com.mhalton.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RAuthor(
    String name,
    @JsonAlias("birth_year")
    int birthYear,
    @JsonAlias("death_year")
    int deathYear
    )
{
}
