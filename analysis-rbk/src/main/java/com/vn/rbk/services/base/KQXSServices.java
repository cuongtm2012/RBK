package com.vn.rbk.services.base;

import com.vn.rbk.domain.*;

import java.util.List;

public interface KQXSServices {
    List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit);

    List<ketqua> getketqua(String ngaychot, Integer skip, Integer limit);

    trend getTrending(String ngaychot);

    caudepDTO caudep(String ngaychot, Integer limit, Integer nhay, Integer lon);

    ketquamn ketquamn(String ngaychot);
}
