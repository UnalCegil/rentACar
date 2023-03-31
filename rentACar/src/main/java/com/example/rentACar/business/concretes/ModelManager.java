package com.example.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.rentACar.business.abstracts.ModelService;
import com.example.rentACar.business.requests.CreateModelRequest;
import com.example.rentACar.business.responses.GetAllModelsResponse;
import com.example.rentACar.core.utulities.mappers.ModelMapperService;
import com.example.rentACar.dataAccess.abstracts.ModelRepository;
import com.example.rentACar.entities.concretes.Model;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService{
	
	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<GetAllModelsResponse> getAll() {
		
		List<Model> models = modelRepository.findAll();
		
		List<GetAllModelsResponse> modelsResponses = models.stream()
				.map(b -> this.modelMapperService.forResponse()
						.map(b, GetAllModelsResponse.class)).collect(Collectors.toList());
		
		return modelsResponses;
	}
	@Override
	public void add(CreateModelRequest createModelRequest) {
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class); 
		
		this.modelRepository.save(model);
		
	}

}
