package com.vn.rbk.repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.vn.rbk.MongoConfig;
import org.springframework.stereotype.Component;

@Component
public class MongoManager {

    public DB initDB(MongoConfig mongoConfig) {
        DB db = null;
        try {
            MongoClient mongo = null;
            mongo = new MongoClient(mongoConfig.getServer(), mongoConfig.getPort());
            return db = mongo.getDB("kqxs");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return db;
    }

    public DBCollection chotKQ(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("chotkq");
    }

    public DBCollection ketqua(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("ketqua");
    }

    public DBCollection caudep(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("caudep");
    }

    public DBCollection caudepsw(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("caudepsw");
    }

    public DBCollection caudep3ngay(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("caudep3ngay");
    }

    public DBCollection trend(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("trend");
    }

    public DBCollection ketquamn(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("ketquamn");
    }

    public DBCollection ketquamt(MongoConfig mongoConfig) {
        return initDB(mongoConfig).getCollection("ketquamt");
    }
}
