package it.polito.tdp.rivers.db;



import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public List<Flow> getAllFlows(int id, Map<Integer, River> idMap) {
		
		final String sql = "SELECT * FROM flow WHERE river = ? ORDER BY day";

		List<Flow> flows = new LinkedList<Flow>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				
				LocalDate date = res.getDate("day").toLocalDate();
		
				
				flows.add(new Flow(date, res.getDouble("flow"), idMap.get(id)));
			}

			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flows;
}}
