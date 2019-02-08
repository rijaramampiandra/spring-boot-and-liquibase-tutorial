package com.java.spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.java.spring.entity.Region;

public interface RegionRepository extends CrudRepository<Region, Integer> {

}
