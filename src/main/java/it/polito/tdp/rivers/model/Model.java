package it.polito.tdp.rivers.model;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	Map<Integer, River> idMap = new TreeMap<Integer, River>();

	
	private RiversDAO riversDAO;
	
	public Model() {
		
		this.riversDAO = new RiversDAO();
		
		
	}
	
		
		public List<River> getRivers() {
		
			for(River r:riversDAO.getAllRivers())
			{
				idMap.put(r.getId(), r);
			}
			
		return riversDAO.getAllRivers();
		
	}
		
		public List<Flow> getFlows(int i){
			
			return riversDAO.getAllFlows(i, idMap);
		}
		
		
	
	
	
}
