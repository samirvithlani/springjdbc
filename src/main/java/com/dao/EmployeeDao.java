package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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

	private final static class Employeemapper implements RowMapper<EmployeeBean> {

		@Override
		public EmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {

			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.seteId(rs.getInt("eid"));
			employeeBean.seteName(rs.getString("ename"));
			employeeBean.seteAge(rs.getString("eage"));
			employeeBean.setrId(rs.getInt("rid"));
			employeeBean.setrName(rs.getString("rname"));
			return employeeBean;
		}

	}

	public List<EmployeeBean> getAllEmployees() {

		return jdbcTemplate.query("select * from employee", new ResultSetExtractor<List<EmployeeBean>>() {

			public List<EmployeeBean> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<EmployeeBean> list = new ArrayList<EmployeeBean>();
				while (rs.next()) {

					EmployeeBean employeeBean = new EmployeeBean();
					employeeBean.seteId(rs.getInt("eid"));
					employeeBean.seteName(rs.getString("ename"));
					employeeBean.seteAge(rs.getString("eage"));
					list.add(employeeBean);

				}

				return list;
			}

		});

	}

	public EmployeeBean getEmployeeByName(String name) {

		return jdbcTemplate.queryForObject("select * from employee where ename ='" + name + "'", new Employeemapper());
	}

	public List<EmployeeBean> emploeeList() {

		return jdbcTemplate.query("select * from employee natural join role", new Employeemapper());
	}

}
