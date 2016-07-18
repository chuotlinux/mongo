package org.krams.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.krams.domain.GeneralInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Service
public class GeneralInfoService {
	
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
	
	public List<GeneralInfo> readAll() throws UnknownHostException {
		init();
		return gis;
		//return giRepository.findAll();
	}
}
