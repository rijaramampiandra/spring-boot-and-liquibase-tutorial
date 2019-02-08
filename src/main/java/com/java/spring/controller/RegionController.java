package com.java.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.spring.entity.Region;
import com.java.spring.service.RegionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/region")
@SwaggerDefinition(tags = { @Tag(name = "/", description = "Region controller.") })
public class RegionController {

	@Autowired
	private RegionService regionService;

	@GetMapping(value = { "/findFirstRegionFound/{description}" })
	@ApiOperation(value = "Find first region by description.")
	public Region findFirstRegionFound(@PathVariable String description) {
		return regionService.findFirstRegionFound(description);
	}

}
