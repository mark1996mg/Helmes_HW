package com.helmes.dao;

import java.util.List;

import com.helmes.model.Sector;

public interface SectorDAO {
	
	public int inserSectors(List<Sector> sectors);
	public List<Sector> listSectors();
}
