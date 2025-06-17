package com.mhalton.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RBook(
    long id,
    String title,
    List<RAuthor> authors,
    List<String> languages,
    @JsonAlias("download_count")
    long downloadCount
    )
{
}
