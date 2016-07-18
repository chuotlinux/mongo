package org.krams.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.krams.domain.GeneralInfo;
import org.krams.service.GeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Controller
public class GeneralInfoController {

	@Autowired
	DB db;
	List<GeneralInfo> gis = new ArrayList<GeneralInfo>();
	
	public void init() throws UnknownHostException {
		DBCollection coll = db.getCollection("generalInfo");
        DBCursor cursor = coll.find();
        while (cursor.hasNext()) { 
           DBObject obj = cursor.next();
           
           GeneralInfo gi = new GeneralInfo();
           gi.setMaCK(obj.get("maCK").toString());
           gi.setTenCty(obj.get("tenCty").toString());
           gis.add(gi);
        }
        System.out.println("--------------------------------------: "+gis);
	}
	
	@RequestMapping
	public ModelAndView getdata() throws UnknownHostException {

		init();
		List<String> list = getList();
		ModelAndView model = new ModelAndView("index");
		//model.addObject("lists", gis);
		model.addObject("lists", list);
		return model;

	}
	private List<String> getList() {

		List<String> list = new ArrayList<String>();
		list.add("List A");
		list.add("List B");
		list.add("List C");
		list.add("List D");
		list.add("List 1");
		list.add("List 2");
		list.add("List 3");

		return list;

	}
}
