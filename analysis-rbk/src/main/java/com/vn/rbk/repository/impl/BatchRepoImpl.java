package com.vn.rbk.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vn.rbk.domain.caudep;
import com.vn.rbk.repository.MongoManager;
import com.vn.rbk.repository.base.BatchRepo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BatchRepoImpl implements BatchRepo {
	public static MongoManager mongo = new MongoManager();
	
	@Override
	public String getKQbyDate(String date) {
		String ketqua = "";
		BasicDBObject query = new BasicDBObject();
		query.put("ngaychot", date);
		
		DBCursor cursor = mongo.ketqua().find(query);
		while (cursor.hasNext()) {
			DBObject dbObject = (DBObject) cursor.next();
			ketqua = dbObject.get("kqAr").toString();
		}
		return ketqua;
	}

	@Override
	public List<caudep> getCaudep() {
		List<caudep> listCaudep = new ArrayList<>();
		DBCursor cursor = mongo.caudep().find();
		while (cursor.hasNext()) {
			caudep cd = new caudep();
			DBObject dbObject = (DBObject) cursor.next();
			cd.setNgaychot(dbObject.get("ngaychot").toString());
			cd.setListCaudep(dbObject.get("listcaudep").toString());
			listCaudep.add(cd);
		}
		return listCaudep;
	}

	@Override
	public List<caudep> getCaudep3Ngay() {
		List<caudep> caudep3ngayList = new ArrayList<>();
		DBCursor cursor = mongo.caudep3ngay().find();
		while (cursor.hasNext()) {
			caudep cd = new caudep();
			DBObject dbObject = (DBObject) cursor.next();
			cd.setNgaychot(dbObject.get("ngaychot").toString());
			cd.setListCaudep(dbObject.get("listcaudep").toString());
			caudep3ngayList.add(cd);
		}
		return caudep3ngayList;
	}

	@Override
	public List<caudep> getCaudepSW() {
		List<caudep> caudepswList = new ArrayList<>();
		DBCursor cursor = mongo.caudepsw().find();
		while (cursor.hasNext()) {
			caudep cd = new caudep();
			DBObject dbObject = (DBObject) cursor.next();
			cd.setNgaychot(dbObject.get("ngaychot").toString());
			cd.setListCaudep(dbObject.get("listcaudep").toString());
			caudepswList.add(cd);
		}
		return caudepswList;
	}

	@Override
	public void updateCaudep(caudep cd) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("listcaudep", cd.getListCaudep());
		BasicDBObject searchQuery = new BasicDBObject().append("ngaychot", cd.getNgaychot());
		mongo.caudep().update(searchQuery, newDocument);
	}

	@Override
	public void updateCaudep3Ngay(caudep cd) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("listcaudep", cd.getListCaudep());
		BasicDBObject searchQuery = new BasicDBObject().append("ngaychot", cd.getNgaychot());
		mongo.caudep3ngay().update(searchQuery, newDocument);
	}

	@Override
	public void updateCaudepSW(caudep cd) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("listcaudep", cd.getListCaudep());
		BasicDBObject searchQuery = new BasicDBObject().append("ngaychot", cd.getNgaychot());
		mongo.caudep3ngay().update(searchQuery, newDocument);	
	}
}
