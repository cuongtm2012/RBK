package com.vn.rbk.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.rbk.domain.caudep;
import com.vn.rbk.repository.base.BatchRepo;
import com.vn.rbk.services.base.BatchServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchServicesImpl implements BatchServices {

	@Autowired
	private BatchRepo batchRepo;

	@Override
	public void update(String date) {
		String kqArr = batchRepo.getKQbyDate(date);
		List<caudep> caudepObj = batchRepo.getCaudep();
		List<caudep> caudep3ngay = batchRepo.getCaudep3Ngay();
		List<caudep> caudepsw = batchRepo.getCaudepSW();

		for (caudep str : caudepObj) {
			String caudepStr = str.getCaudepStr();
			String[] element = str.getCaudepStr().replace("\\\"", "").replaceAll("[", "").replaceAll("]", "").split(",");
			String newElement = "";
			for (int i = 0; i < element.length; i++) {
				if(kqArr.contains(element[i])){
					newElement = caudepStr.replace(element[i], element[i] + "<span>1</span>");
				}
			}
			batchRepo.updateCaudep(str);
		}

		for (caudep str : caudep3ngay) {
			String caudepStr = str.getCaudepStr();
			String[] element = str.getCaudepStr().replace("\\\"", "").replaceAll("[", "").replaceAll("]", "").split(",");
			String newElement = "";
			for (int i = 0; i < element.length; i++) {
				if(kqArr.contains(element[i])){
					newElement = caudepStr.replace(element[i], element[i] + "<span>1</span>");
				}
			}
			batchRepo.updateCaudep3Ngay(str);
		}
		
		for (caudep str : caudepsw) {
			String caudepStr = str.getCaudepStr();
			String[] element = str.getCaudepStr().replace("\\\"", "").replaceAll("[", "").replaceAll("]", "").split(",");
			String newElement = "";
			for (int i = 0; i < element.length; i++) {
				if(kqArr.contains(element[i])){
					newElement = caudepStr.replace(element[i], element[i] + "<span>1</span>");
				}
			}
			batchRepo.updateCaudepSW(str);
		}
	}

}
