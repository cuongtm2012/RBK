/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.rbk.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vn.rbk.AppConfig;
import com.vn.rbk.services.base.RbkServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RbkController {
	@Autowired
	private RbkServices rbkServices;

	@Autowired
	private AppConfig myConfig;

	@GetMapping(path = "/analysis/{date}")
	public String get(@PathVariable("date") String date) {

		String URL = myConfig.getUrl();
		String chotkq = myConfig.getChotkq();
		String trendURL = myConfig.getTrendURL();
		String caudepURL = myConfig.getCaudepURL();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = new Date();
		try {
			myDate = dateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		String todayDateStr = dateFormat.format(myDate);
		log.info(todayDateStr);
		// get data from rongbachkim
		// get list chot ket qua
		chotkq = String.format(chotkq, todayDateStr);
		rbkServices.alsChotKQ(chotkq, todayDateStr);
		// return ket qua object
		URL = String.format(URL, todayDateStr);
		rbkServices.alsKetquasx(URL, todayDateStr);
		// return trend array
		trendURL = String.format(trendURL, todayDateStr);
		rbkServices.alsTrend(trendURL, todayDateStr);
		// return cau dep array
		caudepURL = String.format(caudepURL, todayDateStr);
		rbkServices.alsCaudep(caudepURL, todayDateStr);
		log.info("Finish!!!");
		return date;
	}

}
