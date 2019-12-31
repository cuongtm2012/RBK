package com.vn.rbk.services.impl;

import com.vn.rbk.model.UserDTO;
import com.vn.rbk.repository.base.UserRepo;
import com.vn.rbk.services.base.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;


    @Override
    public boolean login(UserDTO userDTO) {
        boolean isLogin = userRepo.login(userDTO);
        return isLogin;
    }
}
