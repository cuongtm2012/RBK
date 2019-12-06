package com.vn.rbk.services.impl;

import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
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
    public List<ketqua> getketqua(String ngaychot) {
        List<ketqua> ketquaList = kqxsRepo.getKetQua(ngaychot);
        return ketquaList;
    }
}
