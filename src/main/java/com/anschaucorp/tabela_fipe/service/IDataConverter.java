package com.anschaucorp.tabela_fipe.service;

import java.util.List;

public interface IDataConverter {
    <T> T getData (String json, Class<T> dataClass);

    <T> List<T> getList (String json, Class<T> dataClass);
}
