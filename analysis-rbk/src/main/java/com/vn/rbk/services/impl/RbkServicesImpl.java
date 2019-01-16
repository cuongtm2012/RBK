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
		rbkRepo.insertKetQua(kq);
	}
	
	@Override
	public void alsTrend(String inputURL, String date){
		trendArr(inputURL);
	}

	@Override
	public void alsCaudep(String inputURL, String date){
		List<String> listCaudep = caudepArr(inputURL);
		caudep cd = new caudep();
		cd.setNgaychot(date);
		cd.setListCaudep(listCaudep);
		rbkRepo.insertCauDep(cd);
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
				Document doc = Jsoup.parse(content);

				Elements ketquaClass = doc.getElementsByClass("ketqua");

				for (Element element : ketquaClass) {
					String kq_0 = element.getElementsByClass("kq_0").html();
					String kq_1 = element.getElementsByClass("kq_1").html();
					String kq_2 = element.getElementsByClass("kq_2").html();
					String kq_3 = element.getElementsByClass("kq_3").html();
					String kq_4 = element.getElementsByClass("kq_4").html();
					String kq_5 = element.getElementsByClass("kq_5").html();
					String kq_6 = element.getElementsByClass("kq_6").html();
					String kq_7 = element.getElementsByClass("kq_7").html();
					String kq_8 = element.getElementsByClass("kq_8").html();
					String kq_9 = element.getElementsByClass("kq_9").html();
					String kq_10 = element.getElementsByClass("kq_10").html();
					String kq_11 = element.getElementsByClass("kq_11").html();
					String kq_12 = element.getElementsByClass("kq_12").html();
					String kq_13 = element.getElementsByClass("kq_13").html();
					String kq_14 = element.getElementsByClass("kq_14").html();
					String kq_15 = element.getElementsByClass("kq_15").html();
					String kq_16 = element.getElementsByClass("kq_16").html();
					String kq_17 = element.getElementsByClass("kq_17").html();
					String kq_18 = element.getElementsByClass("kq_18").html();
					String kq_19 = element.getElementsByClass("kq_19").html();
					String kq_20 = element.getElementsByClass("kq_20").html();
					String kq_21 = element.getElementsByClass("kq_21").html();
					String kq_22 = element.getElementsByClass("kq_22").html();
					String kq_23 = element.getElementsByClass("kq_23").html();
					String kq_24 = element.getElementsByClass("kq_24").html();
					String kq_25 = element.getElementsByClass("kq_25").html();
					String kq_26 = element.getElementsByClass("kq_26").html();

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
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return kq;
	}
	
	public String[] trendArr(String inputUrl){
		String[] arrayTrend = new String[100];
		try {
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return arrayTrend;
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
}
