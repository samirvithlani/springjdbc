package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.EmploeeBean;
import com.bean.EmployeeBean;

@Repository("empDao")
public class EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addEmployee() {

		String name = "raja";
		// employeeeban;
		System.out.println("jdbc templ.." + jdbcTemplate);
		return jdbcTemplate.update("insert into employee(ename,eage)values(?,?)", name, 20);

	}

	public int deletEmployee(int id) {

		return jdbcTemplate.update("delete from employee where eid =" + id + "");
	}

	// update employee.... hw...
	
	
	
	private final static class Employeemapper implements RowMapper<EmployeeBean>{

		@Override
		public EmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {

			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.seteId(rs.getInt("eid"));
			employeeBean.seteName(rs.getString("ename"));
			employeeBean.seteAge(rs.getString("eage"));
			return employeeBean;
		}
		
		
		
	}
	
	public EmployeeBean getEmployeeByName(String name) {
		
		
		return jdbcTemplate.queryForObject("select * from employee where ename ='"+name+"'", new Employeemapper());
	}
	
	public List<EmployeeBean> emploeeList() {

		return jdbcTemplate.query("select * from employee", new Employeemapper());
	}

}
