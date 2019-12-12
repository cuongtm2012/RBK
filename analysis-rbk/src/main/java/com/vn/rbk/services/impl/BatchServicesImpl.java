package com.vn.rbk.services.impl;

import com.vn.rbk.domain.caudep;
import com.vn.rbk.repository.base.BatchRepo;
import com.vn.rbk.services.base.BatchServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class BatchServicesImpl implements BatchServices {

    @Autowired
    private BatchRepo batchRepo;

    @Override
    public void update(String date) {
        String kqArr = batchRepo.getKQbyDate(date);
        List<caudep> caudepObj = batchRepo.getCaudep();

//        for (caudep cd : caudepObj) {
//            String caudepStr = cd.getListCaudep();
//            String[] element = cd.getListCaudep().replaceAll("\\\"", "").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
//            String newElement = "";
//            for (int i = 0; i < element.length; i++) {
//                if (kqArr.contains(element[i])) {
//                    String nextElement = element[i].trim();
//                    newElement = caudepStr.replaceAll(nextElement, nextElement + "<span>1</span>");
//                }
//            }
//            if (!StringUtils.isEmpty(newElement)) {
//                cd.setListCaudep(newElement);
//            }
//            batchRepo.updateCaudep(cd);
//        }
    }

}
