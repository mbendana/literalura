package com.mhalton.literalura.service;

public interface IConverter
{
    <T> T convertData(String json, Class<T> classType);
}
