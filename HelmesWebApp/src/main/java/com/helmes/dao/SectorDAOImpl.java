package com.helmes.dao;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
			String sql = "INSERT INTO sectors(sectorId, sectorName, childSectors) VALUES(?, ?, ?)";
			java.sql.Array childSectors = listToPSQLArray(sector.getChildSectors());
			rowsInserted += jdbcTemplate.update(sql, sector.getSectorId(), sector.getSectorName(), childSectors);
		}
		return rowsInserted;
		
	}

	@Override
	public List<Sector> listSectors() {
		String sql = "SELECT * FROM sectors";
		RowMapper<Sector> rowMapper = new RowMapper<Sector>() {
			
			@Override
			public Sector mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sector sector = new Sector();
				sector.setSectorId(rs.getInt("sectorid"));
				sector.setSectorName(rs.getString("sectorname"));
				sector.setChildSectors(Arrays.asList((Integer[]) rs.getArray("childsectors").getArray()));
				return sector;
			}
		};
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	private Array listToPSQLArray(List<Integer> list) {
	    Array psqlIntArray = null;
	    try {
	    	psqlIntArray = jdbcTemplate.getDataSource().getConnection().createArrayOf("INTEGER", list.toArray());
	    } 
	    catch (SQLException e) {
	    	System.out.println(e);
	    }
	    return psqlIntArray;
	}
		

}
