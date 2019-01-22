package com.vn.rbk.services.base;

public interface RbkServices {

	void alsChotKQ(String chotkq, String date);

	void alsKetquasx(String inputURL, String date);

	void alsTrend(String inputURL, String date);

	void alsCaudep(String inputURL, String date);

	void alsCaudepSW(String inputURL, String date);

	void alsCau3Ngay(String caudepURL, String todayDateStr);

}
