package com.helmes.model;

import java.util.Collections;
import java.util.List;

public class Sector {
	
	private int sectorId;
	private String sectorName;
	private List<Integer> childSectors;
	
	public Sector() {
		
	}

	public int getSectorId() {
		return sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public List<Integer> getChildSectors() {
		return childSectors;
	}
	
	
	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public void setChildSectors(List<Integer> childSectors) {
		Collections.sort(childSectors);
		this.childSectors = childSectors;
	}
}
