package com.helmes.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.helmes.model.Sector;

public class SectorDAOImpl implements SectorDAO {

	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public int inserSectors(List<Sector> sectors) {
		int rowsInserted = 0;
		for (Sector sector : sectors) {
			String sql = "INSERT INTO sectors(sectorId, sectorName) VALUES(?, ?)";
			rowsInserted += jdbcTemplate.update(sql, sector.getSectorId(), sector.getSectorName());
		}
		return rowsInserted;
		
	}

	@Override
	public List<Sector> listSectors() {
		String sql = "SELECT * FROM sectors";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sector.class));
	}
	

}
