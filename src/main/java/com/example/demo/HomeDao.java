package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class HomeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public List<User> getAllList(){
		String query = "select id, customer from (select ROWNUM as id, ord_id ||'-'||tot_cost||'원'||'-'|| sndr_name||'-'||sndr_id as customer from t_order)";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User getRanDomId(){
		Random rand = new Random();
		int ranDomId =  rand.nextInt(99999)+1;
		String query = "select id, customer  from (select ROWNUM as id, ord_id ||'-'||tot_cost||'원'||'-'|| sndr_name||'-'||sndr_id as customer from t_order) WHERE id = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{ranDomId}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setCustomer(rs.getString(2));
				return user;
			}
			
		});
	}
}
