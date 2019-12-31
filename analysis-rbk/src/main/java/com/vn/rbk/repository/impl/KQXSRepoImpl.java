package com.vn.rbk.repository.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vn.rbk.MongoConfig;
import com.vn.rbk.domain.*;
import com.vn.rbk.repository.MongoManager;
import com.vn.rbk.repository.base.KQXSRepo;
import com.vn.rbk.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Repository
@Slf4j
public class KQXSRepoImpl implements KQXSRepo {
    public static MongoManager mongo = new MongoManager();
    @Autowired
    MongoConfig mongoConfig;

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
                document.put("name", Pattern.compile(name, Pattern.CASE_INSENSITIVE));
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
                cd.setTime((String) dbobject.get("time"));
                listChotKQ.add(cd);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return listChotKQ;
    }

    @Override
    public List<ketqua> getKetQua(String ngaychot, Integer skip, Integer limit) {
        List<ketqua> ketquaList = new ArrayList<>();

        try {
            BasicDBObject document = new BasicDBObject();
            DBCursor cursor;
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", ngaychot);
                cursor = mongo.ketqua(mongoConfig).find(document);
            } else {
                BasicDBObject documentSort = new BasicDBObject();
                documentSort.put("ngaychot", -1);
                cursor = mongo.ketqua(mongoConfig).find().skip(skip).limit(limit).sort(documentSort);
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

                kq.setDau0(dbobject.get("dau0").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau1(dbobject.get("dau1").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau2(dbobject.get("dau2").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau3(dbobject.get("dau3").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau4(dbobject.get("dau4").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau5(dbobject.get("dau5").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau6(dbobject.get("dau6").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau7(dbobject.get("dau7").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau8(dbobject.get("dau8").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDau9(dbobject.get("dau9").toString().replaceAll("\\[", "").replaceAll("\\]", ""));

                kq.setDit0(dbobject.get("dit0").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit1(dbobject.get("dit1").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit2(dbobject.get("dit2").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit3(dbobject.get("dit3").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit4(dbobject.get("dit4").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit5(dbobject.get("dit5").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit6(dbobject.get("dit6").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit7(dbobject.get("dit7").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit8(dbobject.get("dit8").toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                kq.setDit9(dbobject.get("dit9").toString().replaceAll("\\[", "").replaceAll("\\]", ""));

                kq.setNgaychot(dbobject.get("ngaychot").toString());

                BasicDBList kqAr = (BasicDBList) dbobject.get("kqAr");
                List<String> ketquaArr = new ArrayList<>();
                for (int i = 0; i < kqAr.size(); i++) {
                    ketquaArr.add(kqAr.get(i).toString());
                }

                kq.setKqAr(ketquaArr);
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

    @Override
    public caudepDTO getcaudep(String ngaychot, Integer limit, Integer nhay, Integer lon) {
        caudepDTO cd = new caudepDTO();
        caudep cdep = new caudep();
        try {
            BasicDBObject document = new BasicDBObject();
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", ngaychot);
            }

            DBCursor cursor = mongo.caudep(mongoConfig).find(document);
            while (cursor.hasNext()) {
                DBObject dbobject = cursor.next();
                if (limit == 1 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit1nhay1lon0").toString());
                } else if (limit == 2 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit2nhay1lon0").toString());
                } else if (limit == 3 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit3nhay1lon0").toString());
                } else if (limit == 4 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit4nhay1lon0").toString());
                } else if (limit == 5 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay1lon0").toString());
                } else if (limit == 6 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit6nhay1lon0").toString());
                } else if (limit == 7 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit7nhay1lon0").toString());
                } else if (limit == 8 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit8nhay1lon0").toString());
                } else if (limit == 9 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit9nhay1lon0").toString());
                } else if (limit == 10 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit10nhay1lon0").toString());
                } else if (limit == 11 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit11nhay1lon0").toString());
                } else if (limit == 12 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit12nhay1lon0").toString());
                } else if (limit == 13 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit13nhay1lon0").toString());
                } else if (limit == 14 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit14nhay1lon0").toString());
                } else if (limit == 15 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit15nhay1lon0").toString());
                } else if (limit == 16 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit16nhay1lon0").toString());
                } else if (limit == 17 && nhay == 1 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit17nhay1lon0").toString());
                } else if (limit == 1 && nhay == 2 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit1nhay2lon0").toString());
                } else if (limit == 2 && nhay == 2 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit2nhay2lon0").toString());
                } else if (limit == 3 && nhay == 2 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit3nhay2lon0").toString());
                } else if (limit == 4 && nhay == 2 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit4nhay2lon0").toString());
                } else if (limit == 5 && nhay == 2 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 1 && nhay == 3 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 2 && nhay == 3 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 3 && nhay == 3 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 4 && nhay == 3 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 5 && nhay == 3 && lon == 0) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 1 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit1nhay1lon0").toString());
                } else if (limit == 2 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit2nhay1lon0").toString());
                } else if (limit == 3 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit3nhay1lon0").toString());
                } else if (limit == 4 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit4nhay1lon0").toString());
                } else if (limit == 5 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay1lon0").toString());
                } else if (limit == 6 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit6nhay1lon0").toString());
                } else if (limit == 7 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit7nhay1lon0").toString());
                } else if (limit == 8 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit8nhay1lon0").toString());
                } else if (limit == 9 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit9nhay1lon0").toString());
                } else if (limit == 10 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit10nhay1lon0").toString());
                } else if (limit == 11 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit11nhay1lon0").toString());
                } else if (limit == 12 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit12nhay1lon0").toString());
                } else if (limit == 13 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit13nhay1lon0").toString());
                } else if (limit == 14 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit14nhay1lon0").toString());
                } else if (limit == 15 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit15nhay1lon0").toString());
                } else if (limit == 16 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit16nhay1lon0").toString());
                } else if (limit == 17 && nhay == 1 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit17nhay1lon0").toString());
                } else if (limit == 1 && nhay == 2 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit1nhay2lon0").toString());
                } else if (limit == 2 && nhay == 2 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit2nhay2lon0").toString());
                } else if (limit == 3 && nhay == 2 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit3nhay2lon0").toString());
                } else if (limit == 4 && nhay == 2 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit4nhay2lon0").toString());
                } else if (limit == 5 && nhay == 2 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 1 && nhay == 3 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 2 && nhay == 3 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 3 && nhay == 3 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 4 && nhay == 3 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                } else if (limit == 5 && nhay == 3 && lon == 1) {
                    cd.setCaudep(dbobject.get("limit5nhay2lon0").toString());
                }
                cd.setNgaychot(ngaychot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cd;
    }

    @Override
    public ketquamn getketquamn(String ngaychot) {
        ketquamn kq = new ketquamn();
        try {
            BasicDBObject document = new BasicDBObject();
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", java.util.regex.Pattern.compile(ngaychot));
            }
            DBCursor cursor = mongo.ketquamn(mongoConfig).find(document);
            while (cursor.hasNext()) {
                List<ketquamnSub> ketquamnSubList = new ArrayList<>();
                DBObject dbobject = cursor.next();
                String ngaychot1 = dbobject.get("ngaychot").toString();
                BasicDBList lottos = (BasicDBList) dbobject.get("lotto");
                BasicDBObject[] lottoArr = lottos.toArray(new BasicDBObject[0]);
                for (BasicDBObject lotto : lottoArr) {
                    ketquamnSub kqSub = new ketquamnSub();
                    kqSub.setKq0(lotto.get("kq0").toString());
                    kqSub.setKq1(lotto.get("kq1").toString());
                    kqSub.setKq2(lotto.get("kq2").toString());
                    kqSub.setKq3(lotto.get("kq3").toString());
                    kqSub.setKq4(lotto.get("kq4").toString());
                    kqSub.setKq5(lotto.get("kq5").toString());
                    kqSub.setKq6(lotto.get("kq6").toString());
                    kqSub.setKq7(lotto.get("kq7").toString());
                    kqSub.setKq8(lotto.get("kq8").toString());
                    kqSub.setKq9(lotto.get("kq9").toString());
                    kqSub.setKq10(lotto.get("kq10").toString());
                    kqSub.setKq11(lotto.get("kq11").toString());
                    kqSub.setKq12(lotto.get("kq12").toString());
                    kqSub.setKq13(lotto.get("kq13").toString());
                    kqSub.setKq14(lotto.get("kq14").toString());
                    kqSub.setKq15(lotto.get("kq15").toString());
                    kqSub.setKq16(lotto.get("kq16").toString());
                    kqSub.setKq17(lotto.get("kq17").toString());

                    if (Validator.validate(lotto.get("dau0"))) {
                        kqSub.setDau0(lotto.get("dau0").toString());
                    }
                    if (Validator.validate(lotto.get("dau1"))) {
                        kqSub.setDau1(lotto.get("dau1").toString());
                    }
                    if (Validator.validate(lotto.get("dau2"))) {
                        kqSub.setDau1(lotto.get("dau2").toString());
                    }
                    if (Validator.validate(lotto.get("dau3"))) {
                        kqSub.setDau1(lotto.get("dau3").toString());
                    }
                    if (Validator.validate(lotto.get("dau4"))) {
                        kqSub.setDau1(lotto.get("dau4").toString());
                    }
                    if (Validator.validate(lotto.get("dau5"))) {
                        kqSub.setDau1(lotto.get("dau5").toString());
                    }
                    if (Validator.validate(lotto.get("dau6"))) {
                        kqSub.setDau1(lotto.get("dau6").toString());
                    }
                    if (Validator.validate(lotto.get("dau7"))) {
                        kqSub.setDau1(lotto.get("dau7").toString());
                    }
                    if (Validator.validate(lotto.get("dau8"))) {
                        kqSub.setDau1(lotto.get("dau8").toString());
                    }
                    if (Validator.validate(lotto.get("dau9"))) {
                        kqSub.setDau1(lotto.get("dau9").toString());
                    }


                    if (Validator.validate(lotto.get("dit0"))) {
                        kqSub.setDit0(lotto.get("dit0").toString());
                    }
                    if (Validator.validate(lotto.get("dit1"))) {
                        kqSub.setDit1(lotto.get("dit1").toString());
                    }
                    if (Validator.validate(lotto.get("dit2"))) {
                        kqSub.setDit2(lotto.get("dit2").toString());
                    }
                    if (Validator.validate(lotto.get("dit3"))) {
                        kqSub.setDit3(lotto.get("dit3").toString());
                    }
                    if (Validator.validate(lotto.get("dit4"))) {
                        kqSub.setDit4(lotto.get("dit4").toString());
                    }
                    if (Validator.validate(lotto.get("dit5"))) {
                        kqSub.setDit5(lotto.get("dit5").toString());
                    }
                    if (Validator.validate(lotto.get("dit6"))) {
                        kqSub.setDit6(lotto.get("dit6").toString());
                    }
                    if (Validator.validate(lotto.get("dit7"))) {
                        kqSub.setDit7(lotto.get("dit7").toString());
                    }
                    if (Validator.validate(lotto.get("dit8"))) {
                        kqSub.setDit8(lotto.get("dit8").toString());
                    }
                    if (Validator.validate(lotto.get("dit9"))) {
                        kqSub.setDit9(lotto.get("dit9").toString());
                    }

                    kqSub.setNgaychot(lotto.get("ngaychot").toString());
                    kqSub.setLocation(lotto.get("location").toString());

                    ketquamnSubList.add(kqSub);
                }
                kq.setNgaychot(ngaychot1);
                kq.setLotto(ketquamnSubList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    @Override
    public ketquamt getketquamt(String ngaychot) {
        ketquamt kq = new ketquamt();
        try {
            BasicDBObject document = new BasicDBObject();
            if (Validator.validateString(ngaychot)) {
                document.put("ngaychot", java.util.regex.Pattern.compile(ngaychot));
            }
            DBCursor cursor = mongo.ketquamt(mongoConfig).find(document);
            while (cursor.hasNext()) {
                List<ketquamtSub> ketquamtSubList = new ArrayList<>();
                DBObject dbobject = cursor.next();
                String ngaychot1 = dbobject.get("ngaychot").toString();
                BasicDBList lottos = (BasicDBList) dbobject.get("lotto");
                BasicDBObject[] lottoArr = lottos.toArray(new BasicDBObject[0]);
                for (BasicDBObject lotto : lottoArr) {
                    ketquamtSub kqSub = new ketquamtSub();
                    kqSub.setKq0(lotto.get("kq0").toString());
                    kqSub.setKq1(lotto.get("kq1").toString());
                    kqSub.setKq2(lotto.get("kq2").toString());
                    kqSub.setKq3(lotto.get("kq3").toString());
                    kqSub.setKq4(lotto.get("kq4").toString());
                    kqSub.setKq5(lotto.get("kq5").toString());
                    kqSub.setKq6(lotto.get("kq6").toString());
                    kqSub.setKq7(lotto.get("kq7").toString());
                    kqSub.setKq8(lotto.get("kq8").toString());
                    kqSub.setKq9(lotto.get("kq9").toString());
                    kqSub.setKq10(lotto.get("kq10").toString());
                    kqSub.setKq11(lotto.get("kq11").toString());
                    kqSub.setKq12(lotto.get("kq12").toString());
                    kqSub.setKq13(lotto.get("kq13").toString());
                    kqSub.setKq14(lotto.get("kq14").toString());
                    kqSub.setKq15(lotto.get("kq15").toString());
                    kqSub.setKq16(lotto.get("kq16").toString());
                    kqSub.setKq17(lotto.get("kq17").toString());
                    kqSub.setKq17(lotto.get("kq18").toString());

                    if (Validator.validate(lotto.get("dau0"))) {
                        kqSub.setDau0(lotto.get("dau0").toString());
                    }
                    if (Validator.validate(lotto.get("dau1"))) {
                        kqSub.setDau1(lotto.get("dau1").toString());
                    }
                    if (Validator.validate(lotto.get("dau2"))) {
                        kqSub.setDau1(lotto.get("dau2").toString());
                    }
                    if (Validator.validate(lotto.get("dau3"))) {
                        kqSub.setDau1(lotto.get("dau3").toString());
                    }
                    if (Validator.validate(lotto.get("dau4"))) {
                        kqSub.setDau1(lotto.get("dau4").toString());
                    }
                    if (Validator.validate(lotto.get("dau5"))) {
                        kqSub.setDau1(lotto.get("dau5").toString());
                    }
                    if (Validator.validate(lotto.get("dau6"))) {
                        kqSub.setDau1(lotto.get("dau6").toString());
                    }
                    if (Validator.validate(lotto.get("dau7"))) {
                        kqSub.setDau1(lotto.get("dau7").toString());
                    }
                    if (Validator.validate(lotto.get("dau8"))) {
                        kqSub.setDau1(lotto.get("dau8").toString());
                    }
                    if (Validator.validate(lotto.get("dau9"))) {
                        kqSub.setDau1(lotto.get("dau9").toString());
                    }


                    if (Validator.validate(lotto.get("dit0"))) {
                        kqSub.setDit0(lotto.get("dit0").toString());
                    }
                    if (Validator.validate(lotto.get("dit1"))) {
                        kqSub.setDit1(lotto.get("dit1").toString());
                    }
                    if (Validator.validate(lotto.get("dit2"))) {
                        kqSub.setDit2(lotto.get("dit2").toString());
                    }
                    if (Validator.validate(lotto.get("dit3"))) {
                        kqSub.setDit3(lotto.get("dit3").toString());
                    }
                    if (Validator.validate(lotto.get("dit4"))) {
                        kqSub.setDit4(lotto.get("dit4").toString());
                    }
                    if (Validator.validate(lotto.get("dit5"))) {
                        kqSub.setDit5(lotto.get("dit5").toString());
                    }
                    if (Validator.validate(lotto.get("dit6"))) {
                        kqSub.setDit6(lotto.get("dit6").toString());
                    }
                    if (Validator.validate(lotto.get("dit7"))) {
                        kqSub.setDit7(lotto.get("dit7").toString());
                    }
                    if (Validator.validate(lotto.get("dit8"))) {
                        kqSub.setDit8(lotto.get("dit8").toString());
                    }
                    if (Validator.validate(lotto.get("dit9"))) {
                        kqSub.setDit9(lotto.get("dit9").toString());
                    }

                    kqSub.setNgaychot(lotto.get("ngaychot").toString());
                    kqSub.setLocation(lotto.get("location").toString());

                    ketquamtSubList.add(kqSub);
                }
                kq.setNgaychot(ngaychot1);
                kq.setLotto(ketquamtSubList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
}
