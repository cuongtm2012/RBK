/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import com.vn.rbk.AppConfig;
import com.vn.rbk.domain.*;
import com.vn.rbk.services.base.BatchServices;
import com.vn.rbk.services.base.KQXSServices;
import com.vn.rbk.services.base.RbkServices;
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
    private RbkServices rbkServices;

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
        List<chotKQ> chotKQList = kqxsServices.getChotKQ(ngaychot, email, name, skip, limit);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(chotKQList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/ketqua")
    public ResponseEntity<?> ketqua(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot,
            @Valid @NotBlank @RequestParam(value = "skip", defaultValue = "0") Integer skip,
            @Valid @NotBlank @RequestParam(value = "limit", defaultValue = "0") Integer limit
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        List<ketqua> ketquaList = kqxsServices.getketqua(ngaychot, skip, limit);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(ketquaList, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/trending")
    public ResponseEntity<?> trending(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        trend trend = kqxsServices.getTrending(ngaychot);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(trend, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/caudep")
    public ResponseEntity<?> caudep(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot,
            @Valid @NotBlank @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @Valid @NotBlank @RequestParam(value = "nhay", defaultValue = "0") Integer nhay,
            @Valid @NotBlank @RequestParam(value = "lon", defaultValue = "0") Integer lon
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        caudepDTO cd = kqxsServices.caudep(ngaychot, limit, nhay, lon);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(cd, HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping(value = "/limitday")
    public ResponseEntity<?> limitday(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        // get limit day
        String url = myConfig.getCaudepURL();
        url = String.format(url, 5, ngaychot, 1, 1);
        int limitday = rbkServices.limitCaudep(url);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(limitday, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/ketquamn")
    public ResponseEntity<?> ketquamn(
            @RequestParam(value = "ngaychot", defaultValue = "") String ngaychot
    ) {
        logger.info("--------- START ---------- ::" + System.currentTimeMillis());
        ketquamn kq = kqxsServices.ketquamn(ngaychot);
        logger.info("--------- END ---------- ::" + System.currentTimeMillis());
        return new ResponseEntity<>(kq, HttpStatus.OK);
    }
}
