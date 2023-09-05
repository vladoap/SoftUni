package com.example.mobilelele_web.service;

import com.example.mobilelele_web.model.service.UserLoginServiceDto;

public interface UserService {

    boolean login(UserLoginServiceDto userLoginServiceDto);

    void logout();
}
