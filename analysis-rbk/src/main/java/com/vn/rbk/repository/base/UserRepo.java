package com.vn.rbk.repository.base;

import com.vn.rbk.model.UserDTO;

public interface UserRepo {
    boolean login(UserDTO userDTO);
}
