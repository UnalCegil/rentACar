package com.example.rentACar.business.rules;

import org.springframework.stereotype.Service;

import com.example.rentACar.core.utulities.exceptions.BusinessException;
import com.example.rentACar.dataAccess.abstracts.BrandRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
	
	private BrandRepository brandRepository;

	public void checkIfBrandNameExists(String name) {
		if(this.brandRepository.existsByName(name)) {
			throw new BusinessException("Brand already exists");
		}
	}
	
}
