package com.vn.rbk.repository.base;

import com.vn.rbk.domain.caudep;

import java.util.List;

public interface BatchRepo {

    String getKQbyDate(String date);

    List<caudep> getCaudep();

    List<caudep> getCaudep3Ngay();

    List<caudep> getCaudepSW();

    void updateCaudep(caudep str);

    void updateCaudep3Ngay(caudep str);

    void updateCaudepSW(caudep str);
}
