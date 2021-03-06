package com.vn.rbk.services.impl;

import com.vn.rbk.domain.*;
import com.vn.rbk.repository.base.KQXSRepo;
import com.vn.rbk.services.base.KQXSServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KQXSServicesImpl implements KQXSServices {

    @Autowired
    private KQXSRepo kqxsRepo;

    @Override
    public List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit) {
        List<chotKQ> listChotKQ = kqxsRepo.getChotKQ(ngaychot, email, name, skip, limit);
        return listChotKQ;
    }

    @Override
    public List<ketqua> getketqua(String ngaychot, Integer skip, Integer limit) {
        List<ketqua> ketquaList = kqxsRepo.getKetQua(ngaychot, skip, limit);
        return ketquaList;
    }

    @Override
    public trend getTrending(String ngaychot) {
        trend trend = kqxsRepo.getTrending(ngaychot);
        return trend;
    }

    @Override
    public caudepDTO caudep(String ngaychot, Integer limit, Integer nhay, Integer lon) {
        caudepDTO cd = kqxsRepo.getcaudep(ngaychot, limit, nhay, lon);
        return cd;
    }

    @Override
    public ketquamn ketquamn(String ngaychot) {
        ketquamn kq = kqxsRepo.getketquamn(ngaychot);
        return kq;
    }

    @Override
    public ketquamt ketquamt(String ngaychot) {
        ketquamt kq = kqxsRepo.getketquamt(ngaychot);
        return kq;
    }
}
