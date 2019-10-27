package com.helmes.dao;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.helmes.model.Sector;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/webapp/WEB-INF/helmes-webapp-servlet.xml")
@Transactional
public class SectorDAOTest {
	
	@Autowired
	private SectorDAOImpl sectorDAO;
	@Autowired
	private UserDAOImpl userDAO;
	
	private List<Sector> sectors;
	private Sector sector;
	
	@Before
	public void init() {
		JdbcTestUtils.deleteFromTables(userDAO.getJdbcTemplate(), "user_sectors");
		JdbcTestUtils.deleteFromTables(sectorDAO.getJdbcTemplate(), "sectors");
		
		sector = new Sector();
		Sector sector2 = new Sector();
		
		sector.setSectorId(1);
		sector.setSectorName("Metal works");
		sector.setChildSectors(Arrays.asList(new Integer[] {5, 7, 1}));
		
		sector2.setSectorId(2);
		sector2.setSectorName("Printing");
		sector2.setChildSectors(Arrays.asList(new Integer[] {3, 8, 171}));
		
		sectors = new ArrayList<Sector>();
		sectors.add(sector);
		sectors.add(sector2);
	}
	
	@Test
	public void checkIfSectorIsNull() {
		Assert.assertNotNull(sector);
	}
	
	@Test
	public void inserSectors() {
		int rowsInsertedToSector = sectorDAO.inserSectors(sectors);
		Assert.assertTrue(rowsInsertedToSector == 2);
	}

	@Test
	public void listSectors() {
		sectorDAO.inserSectors(sectors);
		sectors = sectorDAO.listSectors();
		Assert.assertTrue(!sectors.isEmpty());
	}

}
