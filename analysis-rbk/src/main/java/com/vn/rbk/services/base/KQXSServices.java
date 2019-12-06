package com.vn.rbk.services.base;

import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;

import java.util.List;

public interface KQXSServices {
    List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit);

    List<ketqua> getketqua(String ngaychot);
}
