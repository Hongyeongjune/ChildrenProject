package org.zerock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.zerock.model.entity.UserNH;

public interface UserNHRepository extends CrudRepository<UserNH, Long>{
	
	List<UserNH> findByName(String name);
	
	List<UserNH> findByNumber(int number);
	
}
