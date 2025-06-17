package com.mhalton.literalura.model;

import java.util.List;

public record RBookList(
        int count,
        String next,
        String previous,
        List<RBook> results
) {}
