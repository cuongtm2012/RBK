/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import com.vn.rbk.AppConfig;
import com.vn.rbk.domain.caudep;
import com.vn.rbk.services.base.BatchServices;
import com.vn.rbk.services.base.RbkServices;
import com.vn.rbk.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/rbk")
public class ImpRbkController {
    @Autowired
    private RbkServices rbkServices;

    @Autowired
    private BatchServices batchServices;

    @Autowired
    private AppConfig myConfig;

    @CrossOrigin
    @GetMapping(value = "/kqxs")
    public void getKQXS() {
        for (int i = 0; i < 1000; i++) {
            LocalDate date = LocalDate.now().minusDays(i);
            String inputDate = DateUtil.newDateYYYYMMDD(date);

            String URL = myConfig.getUrl();
            // return ket qua object
            URL = String.format(URL, inputDate);
            rbkServices.alsKetquasx(URL, inputDate);

            log.info("Get KQXS Finish!!!");
        }
    }

    @CrossOrigin
    @GetMapping(value = "/chotkq")
    public void chotkq() {
        String chotkq = myConfig.getChotkq();
        for (int i = 0; i < 2; i++) {
            LocalDate date = LocalDate.now().minusDays(i);
            String todayDateStr = DateUtil.newDateYYYYMMDD(date);
            // get data from rongbachkim
            // get list chot ket qua
            chotkq = String.format(chotkq, todayDateStr);
            rbkServices.alsChotKQ(chotkq, todayDateStr);
        }
    }

    public void trending() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.format(date);
        System.out.println(dateFormat.format(date));
        try {
            if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("19:00"))) {
                LocalDateTime.from(date.toInstant()).plusDays(1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String trendURL = myConfig.getTrendURL();
        String todayDateStr = DateUtil.dateFormatYYYYMMDD(date);
        // return trend array
        trendURL = String.format(trendURL, todayDateStr);
        rbkServices.alsTrend(trendURL, todayDateStr);
    }

    @CrossOrigin
    @GetMapping(value = "/caudep")
    public void caudep() {
        Date date = new Date();
        String todayDateStr = DateUtil.dateFormatYYYYMMDD(date);
        caudep cd = new caudep();
        // return cau dep array
        for (int limit = 1; limit < 16; limit++) {
            for (int nhay = 1; nhay < 4; nhay++) {
                for (int lon = 0; lon < 2; lon++) {
                    String url = myConfig.getCaudepURL();
                    url = String.format(url, limit, todayDateStr, nhay, lon);
                    rbkServices.alsCaudep(cd, url, limit, todayDateStr, nhay, lon);
                }
            }
        }

        rbkServices.impCaudep(cd);

    }
}
