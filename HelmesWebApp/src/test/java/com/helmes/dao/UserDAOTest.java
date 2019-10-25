package com.helmes.dao;

import java.util.ArrayList;
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
import com.helmes.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:**/webapp/WEB-INF/helmes-webapp-servlet.xml")
@Transactional
public class UserDAOTest {
	
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private SectorDAOImpl sectorDAO;
	
	private User user;
	private List<Sector> sectors;
	private List<Sector> selectedSectors;
	private int rowsInsertedToUser;
	private int rowsInsertedToUserSectors;
	
	@Before
	public void init() {
		JdbcTestUtils.deleteFromTables(userDAO.getJdbcTemplate(), "users");
		JdbcTestUtils.deleteFromTables(sectorDAO.getJdbcTemplate(), "sectors");
		JdbcTestUtils.deleteFromTables(userDAO.getJdbcTemplate(), "user_sectors");
		
		user = new User();
		user.setUserId("1451451NBVFVDV414");
		user.setFullName("Mike Smith");
		user.setHasAgreedToTerms(true);
		
		sectors = new ArrayList<Sector>();
		selectedSectors = new ArrayList<Sector>();
		
		Sector sector1 = new Sector();
		Sector sector2 = new Sector();
		
		sector1.setSectorId(1);
		sector1.setSectorName("Manufacturing");
		
		sector2.setSectorId(2);
		sector2.setSectorName("Construction");
		
		selectedSectors.add(sector1);
		selectedSectors.add(sector2);
		
		user.setSectors(selectedSectors);
		
		Sector sector3 = new Sector();
		Sector sector4 = new Sector();
		
		sector3.setSectorId(3);
		sector3.setSectorName("Services");
		
		sector4.setSectorId(4);
		sector4.setSectorName("Food & Beverage");
		
		sectors.addAll(selectedSectors);
		sectors.add(sector3);
		sectors.add(sector4);
		
		sectorDAO.inserSectors(sectors);
		rowsInsertedToUser = userDAO.insertOrUpdateUser(user);
		rowsInsertedToUserSectors = userDAO.insertOrReplaceUserSectors(user);
		
	}
	
	@Test
	public void checkIfUserIsNull() {
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testInsertOrUpdateUser() {
		// Insert
		Assert.assertTrue(rowsInsertedToUser == 1);
		Assert.assertTrue(rowsInsertedToUserSectors == 2);
		
		// Update
		String oldNameString = user.getFullName();
		user.setFullName("John");
		Sector sector = sectors.get(sectors.size() - 1);
		selectedSectors.add(sector);
		
		rowsInsertedToUser = userDAO.insertOrUpdateUser(user);
		rowsInsertedToUserSectors = userDAO.insertOrReplaceUserSectors(user);
		
		String newName = user.getFullName();
		Assert.assertNotEquals(oldNameString, newName);
		Assert.assertTrue(rowsInsertedToUser == 1);
		Assert.assertTrue(rowsInsertedToUserSectors == 3);
	}
	
	
	@Test
	public void testGetUser() {
		User sessionUser = userDAO.getUser(user.getUserId());
		
		Assert.assertEquals(user.getUserId(), sessionUser.getUserId());
		Assert.assertEquals(user.getFullName(), sessionUser.getFullName());
		Assert.assertEquals(user.getHasAgreedToTerms(), sessionUser.getHasAgreedToTerms());
		
		for (int i = 0; i < sessionUser.getSectors().size(); i++) {
			int userId = user.getSectors().get(i).getSectorId();
			String userName = user.getSectors().get(i).getSectorName();
	
			int sessionUserId = sessionUser.getSectors().get(i).getSectorId();
			String sessionUserName = sessionUser.getSectors().get(i).getSectorName();
			
			Assert.assertEquals(userId, sessionUserId);
			Assert.assertEquals(userName, sessionUserName);
		}
	}

}
