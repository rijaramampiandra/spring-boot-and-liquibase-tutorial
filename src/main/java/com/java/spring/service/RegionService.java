package com.java.spring.service;

import java.util.Optional;

import com.java.spring.entity.Region;

public interface RegionService {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	Region save(Region entity);

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Region> findById(Integer id);

	/**
	 * Find all region.
	 * 
	 * @return all the region.
	 */
	Iterable<Region> findAll();

	Region findFirstRegionFound(String description);

}
