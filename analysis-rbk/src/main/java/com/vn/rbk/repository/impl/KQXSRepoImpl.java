package com.vn.rbk.repository.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vn.rbk.MongoConfig;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
import com.vn.rbk.domain.trend;
import com.vn.rbk.repository.MongoManager;
import com.vn.rbk.repository.base.KQXSRepo;
import com.vn.rbk.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class KQXSRepoImpl implements KQXSRepo {
    @Autowired
    MongoConfig mongoConfig;

    public static MongoManager mongo = new MongoManager();

    @Override
    public List<chotKQ> getChotKQ(String ngaychot, String email, String name, Integer skip, Integer limit) {
        List<chotKQ> listChotKQ = new ArrayList();

        try {
            BasicDBObject document = new BasicDBObject();
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", ngaychot);
            }
            if (Validator.validateString(email)) {
                document.put("email", email);
            }
            if (Validator.validateString(name)) {
                document.put("name", name);
            }

            BasicDBObject sortDocument = new BasicDBObject();
            sortDocument.put("_id", -1);

            DBCursor cursor = mongo.chotKQ(mongoConfig).find(document).skip(skip).limit(limit).sort(sortDocument);
            while (cursor.hasNext()) {
                chotKQ cd = new chotKQ();
                DBObject dbobject = cursor.next();
                BasicDBList listDBlo = (BasicDBList) dbobject.get("lo");
                String[] strLo = new String[listDBlo.size()];
                for (int i = 0; i < listDBlo.size(); i++) {
                    strLo[i] = listDBlo.get(i).toString().replaceAll("\"", "");
                }
                cd.setLo(strLo);
                BasicDBList listDBlodau = (BasicDBList) dbobject.get("lodau");
                String[] strlodau = new String[listDBlodau.size()];
                for (int i = 0; i < listDBlodau.size(); i++) {
                    strlodau[i] = listDBlodau.get(i).toString().replaceAll("\"", "");
                }
                cd.setLodau(strlodau);
                BasicDBList listDBlodit = (BasicDBList) dbobject.get("lodit");
                String[] strlodit = new String[listDBlodit.size()];
                for (int i = 0; i < listDBlodit.size(); i++) {
                    strlodit[i] = listDBlodit.get(i).toString().replaceAll("\"", "");
                }
                cd.setLodit(strlodit);
                cd.setLobt((String) dbobject.get("lobt"));
                BasicDBList listDBdedau = (BasicDBList) dbobject.get("dedau");
                String[] strdedau = new String[listDBdedau.size()];
                for (int i = 0; i < listDBdedau.size(); i++) {
                    strdedau[i] = listDBdedau.get(i).toString().replaceAll("\"", "");
                }
                cd.setDedau(strdedau);
                BasicDBList listDBdedit = (BasicDBList) dbobject.get("dedit");
                String[] strdedit = new String[listDBdedit.size()];
                for (int i = 0; i < listDBdedit.size(); i++) {
                    strdedit[i] = listDBdedit.get(i).toString().replaceAll("\"", "");
                }
                cd.setDedit(strdedit);
                cd.setDebt((String) dbobject.get("debt"));
                cd.setEmail((String) dbobject.get("email"));
                cd.setName((String) dbobject.get("name"));
                cd.setRank((Integer) dbobject.get("rank"));
                cd.setRatio_de((String) dbobject.get("ratio_de"));
                cd.setRatio_lo((String) dbobject.get("ratio_lo"));
                cd.setRatio_lobt((String) dbobject.get("ratio_lobt"));
                cd.setRatio_debt((String) dbobject.get("ratio_debt"));
                listChotKQ.add(cd);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return listChotKQ;
    }

    @Override
    public List<ketqua> getKetQua(String ngaychot) {
        List<ketqua> ketquaList = new ArrayList<>();

        try {
            BasicDBObject document = new BasicDBObject();
            DBCursor cursor;
            if(Validator.validateString(ngaychot)){
                document.put("ngaychot", ngaychot);
                cursor = mongo.ketqua(mongoConfig).find(document);
            } else {
                cursor = mongo.ketqua(mongoConfig).find();
            }

            while (cursor.hasNext()) {
                ketqua kq = new ketqua();
                DBObject dbobject = cursor.next();
                kq.setKq0(dbobject.get("kq0").toString());
                kq.setKq1(dbobject.get("kq1").toString());
                kq.setKq2(dbobject.get("kq2").toString());
                kq.setKq3(dbobject.get("kq3").toString());
                kq.setKq4(dbobject.get("kq4").toString());
                kq.setKq5(dbobject.get("kq5").toString());
                kq.setKq6(dbobject.get("kq6").toString());
                kq.setKq7(dbobject.get("kq7").toString());
                kq.setKq8(dbobject.get("kq8").toString());
                kq.setKq9(dbobject.get("kq9").toString());
                kq.setKq10(dbobject.get("kq10").toString());
                kq.setKq11(dbobject.get("kq11").toString());
                kq.setKq12(dbobject.get("kq12").toString());
                kq.setKq13(dbobject.get("kq13").toString());
                kq.setKq14(dbobject.get("kq14").toString());
                kq.setKq15(dbobject.get("kq15").toString());
                kq.setKq16(dbobject.get("kq16").toString());
                kq.setKq17(dbobject.get("kq17").toString());
                kq.setKq18(dbobject.get("kq18").toString());
                kq.setKq19(dbobject.get("kq19").toString());
                kq.setKq20(dbobject.get("kq20").toString());
                kq.setKq21(dbobject.get("kq21").toString());
                kq.setKq22(dbobject.get("kq22").toString());
                kq.setKq23(dbobject.get("kq23").toString());
                kq.setKq24(dbobject.get("kq24").toString());
                kq.setKq25(dbobject.get("kq25").toString());
                kq.setKq26(dbobject.get("kq26").toString());
                kq.setNgaychot(dbobject.get("ngaychot").toString());
                kq.setKqAr(dbobject.get("kqAr").toString());
                ketquaList.add(kq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ketquaList;
    }

    @Override
    public trend getTrending(String ngaychot) {
        trend trend = new trend();
        try {
            BasicDBObject document = new BasicDBObject();
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", ngaychot);
            }

            DBCursor cursor = mongo.trend(mongoConfig).find(document);
            while (cursor.hasNext()) {
                DBObject dbobject = cursor.next();
                trend.setLotto(dbobject.get("lotto").toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return trend;
    }
}
