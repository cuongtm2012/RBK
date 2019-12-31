/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.vn.rbk.AppConfig;
import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.ketquamnSub;
import com.vn.rbk.services.base.BatchServices;
import com.vn.rbk.services.base.RbkServices;
import com.vn.rbk.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class RbkController {
    @Autowired
    private RbkServices rbkServices;

    @Autowired
    private BatchServices batchServices;

    @Autowired
    private AppConfig myConfig;

    @Scheduled(cron = "0 15-31 18 * * *")
    public void getKQXS() {
        String URL = myConfig.getUrl();
        // return ket qua object
        String todayDDMMYYYY = DateUtil.newDateYYYYMMDD();
        log.info(todayDDMMYYYY);
        URL = String.format(URL, todayDDMMYYYY);
        rbkServices.alsKetquasx(URL, todayDDMMYYYY);

        log.info("Get KQXS Finish!!!");
    }

    @Scheduled(cron = "0 25/55 * * * ? ")
    public void chotkq() {
        String chotkq = myConfig.getChotkq();
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
        String todayDateStr = DateUtil.dateFormatYYYYMMDD(date);
        // get data from rongbachkim
        // get list chot ket qua
        chotkq = String.format(chotkq, todayDateStr);
        rbkServices.alsChotKQ(chotkq, todayDateStr);
    }

    @Scheduled(cron = "0 20/50 * * * ? ")
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

    @Scheduled(cron = "0 0 20 * * *")
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

    @Scheduled(cron = "0 0 19 * * *")
    public void rssmn() {
        try {
            URL feedSource = new URL("https://xskt.com.vn/rss-feed/mien-nam-xsmn.rss");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            List object = feed.getEntries();
            for (Object o : object) {
                String ngaychot = rbkServices.parseDate(((SyndEntryImpl) o).getTitle().toString());
                List<ketquamnSub> ketquamnList = rbkServices.parseNumber(((SyndEntryImpl) o).getDescription().getValue(), ngaychot);
                rbkServices.impkqmn(ketquamnList, ngaychot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
