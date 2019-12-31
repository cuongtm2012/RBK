package com.vn.rbk.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.Data;

@Data
public class ketquamnSub {
    String kq0 = "";
    String kq1 = "";
    String kq2 = "";
    String kq3 = "";
    String kq4 = "";
    String kq5 = "";
    String kq6 = "";
    String kq7 = "";
    String kq8 = "";
    String kq9 = "";
    String kq10 = "";
    String kq11 = "";
    String kq12 = "";
    String kq13 = "";
    String kq14 = "";
    String kq15 = "";
    String kq16 = "";
    String kq17 = "";

    String dau0;
    String dau1;
    String dau2;
    String dau3;
    String dau4;
    String dau5;
    String dau6;
    String dau7;
    String dau8;
    String dau9;

    String dit0;
    String dit1;
    String dit2;
    String dit3;
    String dit4;
    String dit5;
    String dit6;
    String dit7;
    String dit8;
    String dit9;

    String location;
    String ngaychot;


    public DBObject bsonFromPojo() {
        BasicDBObject document = new BasicDBObject();
        document.put("kq0", this.kq0);
        document.put("kq1", this.kq1);
        document.put("kq2", this.kq2);
        document.put("kq3", this.kq3);
        document.put("kq4", this.kq4);
        document.put("kq5", this.kq5);
        document.put("kq6", this.kq6);
        document.put("kq7", this.kq7);
        document.put("kq8", this.kq8);
        document.put("kq9", this.kq9);
        document.put("kq10", this.kq10);
        document.put("kq11", this.kq11);
        document.put("kq12", this.kq12);
        document.put("kq13", this.kq13);
        document.put("kq14", this.kq14);
        document.put("kq15", this.kq15);
        document.put("kq16", this.kq16);
        document.put("kq17", this.kq17);

        document.put("dau0", this.dau0);
        document.put("dau1", this.dau1);
        document.put("dau2", this.dau2);
        document.put("dau3", this.dau3);
        document.put("dau4", this.dau4);
        document.put("dau5", this.dau5);
        document.put("dau6", this.dau6);
        document.put("dau7", this.dau7);
        document.put("dau8", this.dau8);
        document.put("dau9", this.dau9);

        document.put("dit0", this.dit0);
        document.put("dit1", this.dit1);
        document.put("dit2", this.dit2);
        document.put("dit3", this.dit3);
        document.put("dit4", this.dit4);
        document.put("dit5", this.dit5);
        document.put("dit6", this.dit6);
        document.put("dit7", this.dit7);
        document.put("dit8", this.dit8);
        document.put("dit9", this.dit9);

        document.put("location", this.location);
        document.put("ngaychot", this.ngaychot);
        return document;
    }
}
