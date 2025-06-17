package com.mhalton.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Converter
{
    public static <T> T convertData(String json, Class<T> classType)
    {
        ObjectMapper converter = new ObjectMapper();

        try
        {
            return converter.readValue(json, classType);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
