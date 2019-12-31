/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import com.vn.rbk.AppConfig;
import com.vn.rbk.model.UserDTO;
import com.vn.rbk.services.base.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/ums")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServices userServices;

    @Autowired
    private AppConfig myConfig;

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        boolean isCreated = userServices.login(userDTO);
        if (isCreated) {
            return new ResponseEntity<>("New User", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Exist User", HttpStatus.OK);
        }
    }
}
