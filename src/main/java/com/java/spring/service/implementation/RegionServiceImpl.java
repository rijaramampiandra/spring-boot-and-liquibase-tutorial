package com.java.spring.service.implementation;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.spring.entity.Region;
import com.java.spring.repository.RegionRepository;
import com.java.spring.service.RegionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;

	@Transactional
	@Override
	public Region save(Region entity) {
		log.info("Save REGION" + entity.toString());
		return regionRepository.save(entity);
	}

	@Override
	public Optional<Region> findById(Integer id) {
		return regionRepository.findById(id);
	}

	@Override
	public Iterable<Region> findAll() {
		return regionRepository.findAll();
	}

	@Override
	public Region findFirstRegionFound(String description) {
		final List<Region> list = regionRepository.findByDescription(description);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}
}
