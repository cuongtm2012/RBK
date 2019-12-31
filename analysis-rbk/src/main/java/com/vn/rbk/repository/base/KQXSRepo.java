package com.vn.rbk.repository.base;

import com.vn.rbk.domain.*;

import java.util.List;

public interface KQXSRepo {
    List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit);

    List<ketqua> getKetQua(String ngaychot, Integer skip, Integer limit);

    trend getTrending(String ngaychot);

    caudepDTO getcaudep(String ngaychot, Integer limit, Integer nhay, Integer lon);

    ketquamn getketquamn(String ngaychot);
}
