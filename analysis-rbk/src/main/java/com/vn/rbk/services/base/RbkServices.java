package com.vn.rbk.services.base;

import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.ketquamnSub;
import com.vn.rbk.domain.ketquamtSub;

import java.util.List;

public interface RbkServices {

    void alsChotKQ(String chotkq, String date);

    void alsKetquasx(String inputURL, String date);

    void alsTrend(String inputURL, String date);

    void alsCaudep(caudep cd, String inputURL, int limit, String date, int nhay, int lon);

    void impCaudep(caudep cd);

    int limitCaudep(String inputUrl);

    String parseDate(String toString);

    public List<ketquamnSub> parseNumber(String value, String ngaychot);

    void impkqmn(List<ketquamnSub> ketquamnList, String ngaychot);

    String parseDateMT(String toString);

    List<ketquamtSub> parseNumberMT(String value, String ngaychot);

    void impkqmt(List<ketquamtSub> ketquamtSubList, String ngaychot);
}
