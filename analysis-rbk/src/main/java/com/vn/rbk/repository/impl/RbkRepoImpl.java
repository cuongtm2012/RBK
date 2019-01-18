package com.vn.rbk.repository.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
import com.vn.rbk.domain.trend;
import com.vn.rbk.repository.MongoManager;
import com.vn.rbk.repository.base.RbkRepo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class RbkRepoImpl implements RbkRepo {
	public static MongoManager mongo = new MongoManager();

	@Override
	public void insertChotKQ(ArrayList<chotKQ> chotKQlist, String date) {
		try {
			for (chotKQ chotKQ2 : chotKQlist) {
					dltChotKQ(chotKQ2.getEmail(), date);
					BasicDBObject document = new BasicDBObject();
					document.put("ngaychot", date);
					document.put("lo", chotKQ2.getLo());
					document.put("lodau", chotKQ2.getLodau());
					document.put("lodit", chotKQ2.getLodit());
					document.put("lobt", chotKQ2.getLobt());
					document.put("dedau", chotKQ2.getDedau());
					document.put("dedit", chotKQ2.getDedit());
					document.put("debt", chotKQ2.getDebt());
					document.put("email", chotKQ2.getEmail());
					document.put("name", chotKQ2.getName());
					document.put("rank", chotKQ2.getRank());

					document.put("ratio_de", chotKQ2.getRatio_de());
					document.put("ratio_lo", chotKQ2.getRatio_lo());
					document.put("ratio_lobt", chotKQ2.getRatio_lobt());
					document.put("ratio_debt", chotKQ2.getDebt());
					mongo.chotKQ().insert(document);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void insertKetQua(ketqua kq) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", kq.getNgaychot());
			document.put("kq0", kq.getKq0());
			document.put("kq1", kq.getKq1());
			document.put("kq2", kq.getKq1());
			document.put("kq3", kq.getKq1());
			document.put("kq4", kq.getKq1());
			document.put("kq5", kq.getKq1());
			document.put("kq6", kq.getKq1());
			document.put("kq7", kq.getKq1());
			document.put("kq8", kq.getKq1());
			document.put("kq9", kq.getKq1());
			document.put("kq10", kq.getKq1());
			document.put("kq11", kq.getKq1());
			document.put("kq12", kq.getKq1());
			document.put("kq13", kq.getKq1());
			document.put("kq14", kq.getKq1());
			document.put("kq15", kq.getKq1());
			document.put("kq16", kq.getKq1());
			document.put("kq17", kq.getKq1());
			document.put("kq18", kq.getKq1());
			document.put("kq19", kq.getKq1());
			document.put("kq20", kq.getKq1());
			document.put("kq21", kq.getKq1());
			document.put("kq22", kq.getKq1());
			document.put("kq23", kq.getKq1());
			document.put("kq24", kq.getKq1());
			document.put("kq25", kq.getKq1());
			document.put("kq26", kq.getKq1());
			mongo.ketqua().insert(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void insertCauDep(caudep cd) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", cd.getNgaychot());
			document.put("listcaudep", cd.getListCaudep());
			mongo.caudep().insert(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean isExistKetQua(String ngaychot) {
		BasicDBObject query = new BasicDBObject();
		query.put("ngaychot", ngaychot);
		DBCursor dbCursor = mongo.ketqua().find(query);
		while (dbCursor.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistCauDep(String ngaychot) {
		BasicDBObject query = new BasicDBObject();
		query.put("ngaychot", ngaychot);
		DBCursor dbCursor = mongo.caudep().find(query);
		while (dbCursor.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistChotKQ(String email, String ngaychot) {
		BasicDBObject query = new BasicDBObject();
		query.put("email", email);
		query.put("ngaychot", ngaychot);
		DBCursor dbCursor = mongo.chotKQ().find(query);
		while (dbCursor.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public void insertTrend(trend newTrend) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", newTrend.getNgaychot());
			document.put("lotto", newTrend.getLotto());
			mongo.trend().insert(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void dltChotKQ(String email, String date) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", date);
			document.put("email", email);
			mongo.chotKQ().remove(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Override
	public void dltTrend(String date) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", date);
			mongo.trend().remove(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public boolean isExistCauDepSW(String date) {
		BasicDBObject query = new BasicDBObject();
		query.put("ngaychot", date);
		DBCursor dbCursor = mongo.caudepsw().find(query);
		while (dbCursor.hasNext()) {
			return true;
		}
		return false;
	}

	@Override
	public void insertCauDepSW(caudep cd) {
		try {
			BasicDBObject document = new BasicDBObject();
			document.put("ngaychot", cd.getNgaychot());
			document.put("listcaudep", cd.getListCaudep());
			mongo.caudepsw().insert(document);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
