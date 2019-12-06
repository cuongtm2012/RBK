/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import com.vn.rbk.AppConfig;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
import com.vn.rbk.services.base.BatchServices;
import com.vn.rbk.services.base.KQXSServices;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/kqxs")
public class KQXSController {
    public static final Logger logger = LoggerFactory.getLogger(KQXSController.class);
    @Autowired
    private KQXSServices kqxsServices;

    @Autowired
    private BatchServices batchServices;

    @Autowired
    private AppConfig myConfig;

    @CrossOrigin
    @GetMapping(value = "/chotkq")
    public ResponseEntity<?> getChotKQ(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot,
            @RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "name", defaultValue = "") String name,
            @Valid @NotBlank @RequestParam(value = "skip", defaultValue = "0") Integer skip,
            @Valid @NotBlank @RequestParam(value = "limit", defaultValue = "0") Integer limit
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        List<chotKQ> rv001DTOS = kqxsServices.getChotKQ(ngaychot, email, name, skip, limit);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(rv001DTOS, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/ketqua")
    public ResponseEntity<?> ketqua(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        List<ketqua> rv001DTOS = kqxsServices.getketqua(ngaychot);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(rv001DTOS, HttpStatus.OK);
    }

}
