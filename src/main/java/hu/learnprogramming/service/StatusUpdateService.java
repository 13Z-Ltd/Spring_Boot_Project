package hu.learnprogramming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hu.learnprogramming.model.entity.StatusUpdate;
import hu.learnprogramming.model.repository.StatusUpdateDao;

@Service
public class StatusUpdateService {
	
	@Value("${status.pagesize}")
	private int pageSize; 
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;
	
	public void save(StatusUpdate statusUpdate) {
		statusUpdateDao.save(statusUpdate);		
	}
	
	public StatusUpdate getLatest() {
		return statusUpdateDao.findFirstByOrderByAddedDesc();
	}
	
	public Page<StatusUpdate> getPage(int pageNumber) {
		PageRequest request = PageRequest.of(pageNumber-1, pageSize, Sort.Direction.DESC, "added");

		return statusUpdateDao.findAll(request);
	}

	public void delete(Long id) {
		statusUpdateDao.deleteById(id);		
	}

	public StatusUpdate get(Long id) {
		return statusUpdateDao.findById(id).get();		 
	}
}
