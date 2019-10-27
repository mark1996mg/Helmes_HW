package com.helmes.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helmes.dao.SectorDAO;
import com.helmes.model.Sector;

@RestController
@RequestMapping("/sector")
public class SectorController {

	@Autowired
	private SectorDAO sectorDAO;
	
	@GetMapping("/get/{sectorId}")
	public Sector getSector(@PathVariable Integer sectorId) {
		for (Sector sector : getAllSectors()) {
			if (sectorId == sector.getSectorId()) {
				return sector;
			}
		}
		return null;
	}
	
	@GetMapping("/getAll")
	public List<Sector> getAllSectors() {
		List<Sector> sortSectors = getSectorsForDisplaying(sectorDAO.listSectors());
		return sortSectors;
	}
	
	@PostMapping("/post")
	public List<Sector> postSectors(@RequestBody List<Sector> sectors) {
		if (sectors != null) {
			sectorDAO.inserSectors(sectors);
		}
		
		return sectors;
	}
	
	
	// Depth first search
	public List<Sector> getSectorsForDisplaying(List<Sector> sectors) {
		String depthNbsp = "";
		List<Sector> sortedSectors = new ArrayList<Sector>();
		
		Stack<Sector> stack = new Stack<Sector>();
		sectors.sort(Comparator.comparing(Sector::getSectorId));
		stack.push(sectors.get(0));
		
		while (!stack.isEmpty()) {
			Sector sector = stack.peek();
			List<Integer> childSectors = new ArrayList<Integer>(sector.getChildSectors());
			Collections.sort(childSectors, Collections.reverseOrder());
			
			if (!childSectors.isEmpty()) {
				depthNbsp += "\u00a0\u00a0\u00a0\u00a0";
				
				int childSectorId = childSectors.remove(0);
				sector.setChildSectors(childSectors);
				for (Sector s : sectors) {
					if (childSectorId == s.getSectorId()) {
						stack.add(s);
						break;
					}
				}
			}
			else {
				Sector s = stack.pop();
				
				if (depthNbsp.length() != 0) {
					depthNbsp = depthNbsp.substring(0, depthNbsp.length() - 4);
					s.setSectorName(depthNbsp + s.getSectorName());
					sortedSectors.add(s);
				}
			}
			
		}
		return sortedSectors;
	}	
}
