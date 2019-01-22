package com.vn.rbk.repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoManager {
	DB db = null;

	public DB initDB() {
		MongoClient mongo = null;
		mongo = new MongoClient("localhost", 27017);
		return db = mongo.getDB("testdb");
	}

	public DBCollection chotKQ() {
		return initDB().getCollection("chotkq");
	}

	public DBCollection ketqua() {
		return initDB().getCollection("ketqua");
	}

	public DBCollection caudep() {
		return initDB().getCollection("caudep");
	}

	public DBCollection caudepsw() {
		return initDB().getCollection("caudepsw");
	}
	
	public DBCollection caudep3ngay() {
		return initDB().getCollection("caudep3ngay");
	}
	
	public DBCollection trend() {
		return initDB().getCollection("trend");
	}
}
