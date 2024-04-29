package com.spyker.iot.vmp.service;

import com.spyker.iot.vmp.storager.dao.dto.UserApiKey;

public interface IUserApiKeyService {
    int addApiKey(UserApiKey userApiKey);

    boolean isApiKeyExists(String apiKey);

    int enable(Integer id);

    int disable(Integer id);

    int remark(Integer id, String remark);

    int delete(Integer id);

    UserApiKey getUserApiKeyById(Integer id);

    int reset(Integer id, String apiKey);
}