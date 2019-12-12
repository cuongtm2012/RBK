package com.vn.rbk.services.base;

import com.vn.rbk.domain.caudep;

public interface RbkServices {

    void alsChotKQ(String chotkq, String date);

    void alsKetquasx(String inputURL, String date);

    void alsTrend(String inputURL, String date);

    void alsCaudep(caudep cd, String inputURL, int limit, String date, int nhay, int lon);

    void impCaudep(caudep cd);
}
