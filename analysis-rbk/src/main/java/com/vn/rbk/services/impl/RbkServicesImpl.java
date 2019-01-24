package com.vn.rbk.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vn.rbk.domain.caudep;
import com.vn.rbk.domain.chotKQ;
import com.vn.rbk.domain.ketqua;
import com.vn.rbk.domain.trend;
import com.vn.rbk.repository.base.RbkRepo;
import com.vn.rbk.services.base.RbkServices;
import com.vn.rbk.util.Utility;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RbkServicesImpl implements RbkServices{
	
	@Autowired
	private RbkRepo rbkRepo;
	
	@Override
	public void alsChotKQ(String chotkq, String date){
		ArrayList<chotKQ> arrChotKQ = parseChotKQ(chotkq);
		rbkRepo.insertChotKQ(arrChotKQ, date);
	}
	
	@Override
	public void alsKetquasx(String inputURL, String date){
		ketqua kq = ketquasx(inputURL);
		kq.setNgaychot(date);
		boolean isExistKQ = rbkRepo.isExistKetQua(date);
		if(!isExistKQ){
			rbkRepo.insertKetQua(kq);
		}
	}
	
	@Override
	public void alsTrend(String inputURL, String date){
		List<String> trending = trendArr(inputURL);
		trend newTrend = new trend();
		newTrend.setLotto(trending);
		newTrend.setNgaychot(date);
		rbkRepo.dltTrend(date);
		rbkRepo.insertTrend(newTrend);
	}

	@Override
	public void alsCaudep(String inputURL, String date){
		List<String> listCaudep = caudepArr(inputURL);
		caudep cd = new caudep();
		cd.setNgaychot(date);
		cd.setListCaudep(listCaudep);
		if(!rbkRepo.isExistCauDep(date)){
			rbkRepo.insertCauDep(cd);
		}
	}
	
	@Override
	public void alsCaudepSW(String inputURL, String date){
		List<String> listCaudepSW = caudepArrSW(inputURL);
		caudep cd = new caudep();
		cd.setNgaychot(date);
		cd.setListCaudep(listCaudepSW);
		if(!rbkRepo.isExistCauDepSW(date)){
			rbkRepo.insertCauDepSW(cd);
		}
	}
	

	@Override
	public void alsCau3Ngay(String inputURL, String date) {
		List<String> listCau3Ngay = caudep3ngay(inputURL);
		caudep cd = new caudep();
		cd.setNgaychot(date);
		cd.setListCaudep(listCau3Ngay);
		if(!rbkRepo.isExistCauDep3Ngay(date)){
			rbkRepo.insertCauDep3Ngay(cd);
		}
	}
	
	public ArrayList<chotKQ> parseChotKQ(String chotkq) {
		String content = "";
		JSONArray de;
		JSONArray lo;
		JSONArray lodau;
		JSONArray lodit;
		String lobt = "";
		JSONArray dedau;
		JSONArray dedit;
		String debt = "";
		String email = "";
		String name = "";
		Object rank;
		Integer rankInt = 0;

		JSONArray ratio_de;
		JSONArray ratio_lo;
		JSONArray ratio_lobt;
		JSONArray ratio_debt;
		
		String ratio_de_str = "";
		String ratio_lo_str = "";
		String ratio_lobt_str = "";
		String ratio_debt_str = "";

		ArrayList<chotKQ> listChotKQ = new ArrayList<chotKQ>();

		try {
			content = Utility.obtainContent(chotkq);
			JSONObject json = new JSONObject(content);
			JSONArray arrChotKQ = json.getJSONArray("list");
			for (Object object : arrChotKQ) {
				chotKQ chotKQObj = new chotKQ();
				JSONObject obj = (JSONObject) object;
				log.info(obj.toString());
				de = obj.getJSONArray("de");
				String[] deArray = de.join(",").split(",");
				lo = obj.getJSONArray("lo");
				String[] loArray = lo.join(",").split(",");
				lodau = obj.getJSONArray("lodau");
				String[] lodauArray = lodau.join(",").split(",");
				lodit = obj.getJSONArray("lodit");
				String[] loditArray = lodit.join(",").split(",");
				lobt = obj.getString("lobt");
				dedau = obj.getJSONArray("dedau");
				String[] dedauArray = dedau.join(",").split(",");
				dedit = obj.getJSONArray("dedit");
				String[] deditArray = dedit.join(",").split(",");
				debt = obj.getString("debt");
				email = obj.getString("email");
				name = obj.getString("name");
				if(obj.has("rank")){
					rank = obj.get("rank");
					if(rank instanceof Integer){
					    log.info(rank.toString());;
					    rankInt = rank.hashCode();
					}
				}
				if(obj.has("ratio") && !obj.isNull("ratio") && !obj.get("ratio").toString().equals("[]")){
					JSONObject ratio = obj.getJSONObject("ratio");
					if(ratio.has("de")){
						ratio_de = ratio.getJSONArray("de");
						ratio_de_str = ratio_de.get(0).toString();
					}
					if(ratio.has("lo")){
						ratio_lo = ratio.getJSONArray("lo");
						ratio_lo_str = ratio_lo.get(0).toString();
					}
					if(ratio.has("lobt")){
						ratio_lobt = ratio.getJSONArray("lobt");
						ratio_lobt_str = ratio_lobt.get(0).toString();
					}
					if(ratio.has("debt")){
						ratio_debt = ratio.getJSONArray("debt");
						ratio_debt_str = ratio_debt.get(0).toString();
					}
				}

				// save to Object
				chotKQObj.setDe(deArray);
				chotKQObj.setLo(loArray);
				chotKQObj.setLodau(lodauArray);
				chotKQObj.setLodit(loditArray);
				chotKQObj.setLobt(lobt);
				chotKQObj.setDedau(dedauArray);
				chotKQObj.setDedit(deditArray);
				chotKQObj.setDebt(debt);
				chotKQObj.setEmail(email);
				chotKQObj.setName(name);
				chotKQObj.setRank(rankInt);
				chotKQObj.setRatio_de(ratio_de_str);
				chotKQObj.setRatio_lo(ratio_lo_str);
				chotKQObj.setRatio_lobt(ratio_lobt_str);
				chotKQObj.setRatio_debt(ratio_debt_str);

				// Add to List
				listChotKQ.add(chotKQObj);

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listChotKQ;
	}

	public ketqua ketquasx(String inputURL) {
		ketqua kq = new ketqua();

		try {
			String content = Utility.obtainContent(inputURL);

			if (!StringUtils.isEmpty(content)) {
				content = content.substring(17,202).replaceAll("\"", "");
				String[] kqArr = content.split(",");
					String kq_0 = kqArr[0].toString();
					String kq_1 = kqArr[1].toString();
					String kq_2 = kqArr[2].toString();
					String kq_3 = kqArr[3].toString();
					String kq_4 = kqArr[4].toString();
					String kq_5 = kqArr[5].toString();
					String kq_6 = kqArr[6].toString();
					String kq_7 = kqArr[7].toString();
					String kq_8 = kqArr[8].toString();
					String kq_9 = kqArr[9].toString();
					String kq_10 = kqArr[10].toString();
					String kq_11 = kqArr[11].toString();
					String kq_12 = kqArr[12].toString();
					String kq_13 = kqArr[13].toString();
					String kq_14 = kqArr[14].toString();
					String kq_15 = kqArr[15].toString();
					String kq_16 = kqArr[16].toString();
					String kq_17 = kqArr[17].toString();
					String kq_18 = kqArr[18].toString();
					String kq_19 = kqArr[19].toString();
					String kq_20 = kqArr[20].toString();
					String kq_21 = kqArr[21].toString();
					String kq_22 = kqArr[22].toString();
					String kq_23 = kqArr[23].toString();
					String kq_24 = kqArr[24].toString();
					String kq_25 = kqArr[25].toString();
					String kq_26 = kqArr[26].toString();
					
					List<String> kqAr = new ArrayList<>();
					kqAr.add(kq_0.substring(kq_0.length() - 2, kq_0.length()));
					kqAr.add(kq_1.substring(kq_1.length() - 2, kq_1.length()));
					kqAr.add(kq_2.substring(kq_2.length() - 2, kq_2.length()));
					kqAr.add(kq_3.substring(kq_3.length() - 2, kq_3.length()));
					kqAr.add(kq_4.substring(kq_4.length() - 2, kq_4.length()));
					kqAr.add(kq_5.substring(kq_5.length() - 2, kq_5.length()));
					kqAr.add(kq_6.substring(kq_6.length() - 2, kq_6.length()));
					kqAr.add(kq_7.substring(kq_7.length() - 2, kq_7.length()));
					kqAr.add(kq_8.substring(kq_8.length() - 2, kq_8.length()));
					kqAr.add(kq_9.substring(kq_9.length() - 2, kq_9.length()));
					kqAr.add(kq_10.substring(kq_10.length() - 2, kq_10.length()));
					kqAr.add(kq_11.substring(kq_11.length() - 2, kq_11.length()));
					kqAr.add(kq_12.substring(kq_12.length() - 2, kq_12.length()));
					kqAr.add(kq_13.substring(kq_13.length() - 2, kq_13.length()));
					kqAr.add(kq_14.substring(kq_14.length() - 2, kq_14.length()));
					kqAr.add(kq_15.substring(kq_15.length() - 2, kq_15.length()));
					kqAr.add(kq_16.substring(kq_16.length() - 2, kq_16.length()));
					kqAr.add(kq_17.substring(kq_17.length() - 2, kq_17.length()));
					kqAr.add(kq_18.substring(kq_18.length() - 2, kq_18.length()));
					kqAr.add(kq_19.substring(kq_19.length() - 2, kq_19.length()));
					kqAr.add(kq_20.substring(kq_20.length() - 2, kq_20.length()));
					kqAr.add(kq_21.substring(kq_21.length() - 2, kq_21.length()));
					kqAr.add(kq_22.substring(kq_22.length() - 2, kq_22.length()));
					kqAr.add(kq_23.substring(kq_23.length() - 2, kq_23.length()));
					kqAr.add(kq_24.substring(kq_24.length() - 2, kq_24.length()));
					kqAr.add(kq_25.substring(kq_25.length() - 2, kq_25.length()));
					kqAr.add(kq_26.substring(kq_26.length() - 2, kq_26.length()));

					kq.setKqAr(kqAr.toString());
					kq.setKq0(kq_0);
					kq.setKq1(kq_1);
					kq.setKq2(kq_2);
					kq.setKq3(kq_3);
					kq.setKq4(kq_4);
					kq.setKq5(kq_5);
					kq.setKq6(kq_6);
					kq.setKq7(kq_7);
					kq.setKq8(kq_8);
					kq.setKq9(kq_9);
					kq.setKq10(kq_10);
					kq.setKq11(kq_11);
					kq.setKq12(kq_12);
					kq.setKq13(kq_13);
					kq.setKq14(kq_14);
					kq.setKq15(kq_15);
					kq.setKq16(kq_16);
					kq.setKq17(kq_17);
					kq.setKq18(kq_18);
					kq.setKq19(kq_19);
					kq.setKq20(kq_20);
					kq.setKq21(kq_21);
					kq.setKq22(kq_22);
					kq.setKq23(kq_23);
					kq.setKq24(kq_24);
					kq.setKq25(kq_25);
					kq.setKq26(kq_26);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return kq;
	}
	
	public List<String> trendArr(String inputUrl){
		List<String> trending = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(inputUrl).get();
			Elements ketquaClass = doc.getElementsByClass("trend_number");
			for (Element element : ketquaClass) {
				String sodep = element.getElementsByClass("trend_number").html();
				trending.add(sodep);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return trending;
	}
	
	public List<String> caudepArr(String inputUrl){
		List<String> listCaudep = new ArrayList<>();
		try {
			if (!StringUtils.isEmpty(inputUrl)) {
				Document doc = Jsoup.connect(inputUrl).get();
				Elements ketquaClass = doc.getElementsByClass("a_cau");
				for (Element element : ketquaClass) {
					String sodep = element.getElementsByClass("a_cau").html();
					listCaudep.add(sodep);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCaudep;
	}
	
	public List<String> caudepArrSW(String inputUrl){
		List<String> listCaudep = new ArrayList<>();
		try {
			if (!StringUtils.isEmpty(inputUrl)) {
				Document doc = Jsoup.connect(inputUrl).get();
				Elements ketquaClass = doc.getElementsByClass("tbl1");
				for (Element element : ketquaClass) {
					Elements sodep = element.getElementsByTag("tr");
					for (Element el : sodep) {
						String col1 = el.getElementsByClass("col1").html();
						Integer col2 = Integer.parseInt(el.getElementsByClass("col2").html().substring(0, 1));
						if(col2 > 3){
							listCaudep.add(col1);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCaudep;
	}
	
	public List<String> caudep3ngay(String inputUrl){
		int count = 0;
		List<String> listCaudep = new ArrayList<>();
		try {
			if (!StringUtils.isEmpty(inputUrl)) {
				Document doc = Jsoup.connect(inputUrl).get();
				Elements ketquaClass = doc.getElementsByClass("tbl1");
				for (Element element : ketquaClass) {
					Elements sodep = element.getElementsByTag("tr");
					for (Element el : sodep) {
						count++;
						String col1 = el.getElementsByClass("col1").html();
						listCaudep.add(col1);
						if(count > 2){
							return listCaudep;
						}
					}
					
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return listCaudep;
	}
}
