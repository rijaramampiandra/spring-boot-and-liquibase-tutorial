package com.java.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.java.spring.entity.Region;

public interface RegionRepository extends CrudRepository<Region, Integer> {

	List<Region> findByDescription(String description);

}
