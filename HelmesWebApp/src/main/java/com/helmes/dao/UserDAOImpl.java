package com.helmes.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.helmes.model.Sector;
import com.helmes.model.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public int insertOrUpdateUser(User user) {
		
		String sql = "INSERT INTO users(userId, fullName, hasAgreedToTerms) VALUES(?, ?, ?)"
				+ "ON CONFLICT (userId) DO UPDATE SET fullName=EXCLUDED.fullName, hasAgreedToTerms=EXCLUDED.hasAgreedToTerms";
		int rowsInserted = jdbcTemplate.update(sql, user.getUserId(), user.getFullName(), user.getHasAgreedToTerms());
		
		insertOrReplaceUserSectors(user);
		return rowsInserted;
		
		
	}
	
	@Override
	public int insertOrReplaceUserSectors(User user) {
		
		int rowsInserted = 0;
		String sql = "DELETE FROM user_sectors WHERE userId=?";
		jdbcTemplate.update(sql, user.getUserId());
		
		for (Sector sector : user.getSectors()) {
			sql = "INSERT INTO user_sectors(userId, sectorId) VALUES(?, ?)";
			rowsInserted += jdbcTemplate.update(sql, user.getUserId(), sector.getSectorId());
		}
		return rowsInserted;
	}


	@Override
	public User getUser(String sessionId) {
		String sql = "SELECT * FROM users WHERE userId='" + sessionId + "'";
		try {
			User user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class));
			sql = "SELECT user_sectors.sectorId, sectors.sectorName FROM user_sectors "
					+ "INNER JOIN sectors ON user_sectors.sectorId = sectors.sectorId "
					+ "WHERE user_sectors.userId ='" + sessionId + "'";
			
			List<Sector> sectors =  jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sector.class));
			user.setSectors(sectors);
			
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


}
