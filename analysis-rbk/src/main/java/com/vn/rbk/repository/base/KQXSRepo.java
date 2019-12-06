package com.vn.rbk.repository.base;

import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;

import java.util.List;

public interface KQXSRepo {
    List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit);

    List<ketqua> getKetQua(String ngaychot);
}
