package com.techvify.interview.service.serviceImp;

import com.techvify.interview.entity.Level;
import com.techvify.interview.payLoad.request.LevelRequestForCreating;
import com.techvify.interview.payLoad.request.UpdatingLevelRequest;
import com.techvify.interview.repository.ILevelRepository;
import com.techvify.interview.service.interfaceservice.ILevelService;
import com.techvify.interview.specification.LevelSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class LevelService implements ILevelService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ILevelRepository repository;

	@Override
	public Page<Level> getAllLevels(Pageable pageable, String search) {
		Specification<Level> where = LevelSpecification.buildWhere(search);
		return repository.findAll(where,pageable);
	}

	@Override
	public Level getLevelByID(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void createLevel(LevelRequestForCreating form) {
		
		Level level = modelMapper.map(form, Level.class);
		repository.save(level);
	}

	@Override
	public void updateLevel(int id, UpdatingLevelRequest level) {
		Level levelUpdate = modelMapper.map(level, Level.class);
		levelUpdate.setId(id);
		repository.save(levelUpdate);
		
	}

	@Override
	public void deleteLevel(int id) {
		 repository.deleteById(id);
	}

	@Override
	public boolean isLevelExistsByCode(String code) {
		return repository.existsByCode(code);
	}

	@Override
	public boolean isLevelExistsByID(Integer id) {
		return repository.existsById(id);
	}
	
}
