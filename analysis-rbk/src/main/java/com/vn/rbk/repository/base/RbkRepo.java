package com.vn.rbk.repository.base;

import java.util.ArrayList;

import com.mongodb.DBCollection;
import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
import com.vn.rbk.domain.trend;

public interface RbkRepo {

	void insertChotKQ(ArrayList<chotKQ> chotKQlist, String date);

	void insertKetQua(ketqua kq);

	void insertCauDep(caudep cd);

	boolean isExistKetQua(String ngaychot);

	boolean isExistCauDep(String ngaychot);

	boolean isExistChotKQ(String email, String ngaychot);

	void insertTrend(trend newTrend);
	
	void dltTrend(String date);

	void dltChotKQ(DBCollection chotKQCl, String email, String date);

	boolean isExistCauDepSW(String date);

	void insertCauDepSW(caudep cd);

	boolean isExistCauDep3Ngay(String date);

	void insertCauDep3Ngay(caudep cd);
	
}
