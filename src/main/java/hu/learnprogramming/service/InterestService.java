package hu.learnprogramming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.learnprogramming.model.entity.Interest;
import hu.learnprogramming.model.repository.InterestDao;

@Service
public class InterestService {
	@Autowired
	private InterestDao interestDao;
	
	public long count() {
		return interestDao.count();
	}
	
	public Interest get(String interestName) {
		return interestDao.findOneByName(interestName);
	}
	
	public void save(Interest interest) {
		interestDao.save(interest);
	}
	
	public Interest createIfNotExist(String interestText) {
		Interest interest = interestDao.findOneByName(interestText);
		
		if(interest == null) {
			interest = new Interest(interestText);
			interestDao.save(interest);
		}
		return interest;
	}
}
