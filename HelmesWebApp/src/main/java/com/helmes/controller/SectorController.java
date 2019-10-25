package com.helmes.controller;

import java.util.List;

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
		return sectorDAO.listSectors();
	}
	
	@PostMapping("/post")
	public List<Sector> postSectors(@RequestBody List<Sector> sectors) {
		if (sectors != null) {
			sectorDAO.inserSectors(sectors);
		}
		return sectors;
	}
}
