package hu.learnprogramming.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import hu.learnprogramming.model.entity.StatusUpdate;

@Repository
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long>{	
	StatusUpdate findFirstByOrderByAddedDesc();
}
