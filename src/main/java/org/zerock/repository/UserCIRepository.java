package org.zerock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.zerock.model.entity.UserCI;

public interface UserCIRepository extends CrudRepository<UserCI, Long>{

	List<UserCI> findByName(String name);
	
	List<UserCI> findByNumber(int number);
	
}
