package com.vn.rbk.repository.base;

import java.util.ArrayList;

import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;

public interface RbkRepo {

	void insertChotKQ(ArrayList<chotKQ> chotKQlist, String date);

	void insertKetQua(ketqua kq);

	void insertCauDep(caudep cd);

}
