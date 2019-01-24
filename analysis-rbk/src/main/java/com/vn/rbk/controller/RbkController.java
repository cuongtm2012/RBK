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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.rbk.AppConfig;
import com.vn.rbk.services.base.BatchServices;
import com.vn.rbk.services.base.RbkServices;
import com.vn.rbk.util.GmailMsg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RbkController {
	@Autowired
	private RbkServices rbkServices;
	
	@Autowired
	private BatchServices batchServices;

	@Autowired
	private AppConfig myConfig;

	@GetMapping(path = "/analysis/{date}")
	public String get(@PathVariable("date") String date) {

		String URL = myConfig.getUrl();
		String chotkq = myConfig.getChotkq();
		String trendURL = myConfig.getTrendURL();
		String caudepURL = myConfig.getCaudepURL();
		String caudepURLSW = myConfig.getCaudepURL();
		String cau3ngay = myConfig.getCau3ngay();

		String todayDateStr = convertDateYYYYMMDD(date);
		log.info(todayDateStr);
		// get data from rongbachkim
		// get list chot ket qua
		chotkq = String.format(chotkq, todayDateStr);
		rbkServices.alsChotKQ(chotkq, todayDateStr);
		// return ket qua object
		String todayDDMMYYYY = convertDateDDMMYYYY(date);
		URL = String.format(URL, todayDDMMYYYY);
		rbkServices.alsKetquasx(URL, todayDateStr);
		// return trend array
		trendURL = String.format(trendURL, todayDateStr);
		rbkServices.alsTrend(trendURL, todayDateStr);
		// return cau dep array
		caudepURL = String.format(caudepURL, todayDateStr, 0);
		rbkServices.alsCaudep(caudepURL, todayDateStr);
		// return cau dep SW array
		caudepURLSW = String.format(caudepURLSW, todayDateStr, 1);
		rbkServices.alsCaudepSW(caudepURLSW, todayDateStr);
		// return cau 3 ngay
		caudepURL = String.format(cau3ngay, todayDateStr);
		rbkServices.alsCau3Ngay(caudepURL, todayDateStr);
		
		GmailMsg.process(date, "");
		
		log.info("Finish!!!");
		return date;
	}

	@PutMapping(path = "/analysis")
	public void updateBatch(){
		batchServices.update(newDateYYYYMMDD());
	}
	
	
	private String convertDateDDMMYYYY(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat convert = new SimpleDateFormat("dd/MM/yyyy");
		Date myDate = new Date();
		try {
			myDate = dateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return convert.format(myDate);
	}

	private String convertDateYYYYMMDD(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = new Date();
		try {
			myDate = dateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return dateFormat.format(myDate);
	}
	

	private String newDateYYYYMMDD() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date myDate = new Date();
		return dateFormat.format(myDate);
	}

}
