package hu.learnprogramming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import hu.learnprogramming.model.dto.SearchResult;
import hu.learnprogramming.model.entity.Profile;
import hu.learnprogramming.model.repository.ProfileDao;

@Service
public class SearchService {
	
	@Value("${results.pagesize}")
	private int pageSize; 
	
	@Autowired
	private ProfileDao profileDao;
	
	//public List<Profile> search(String text) {
	public Page<SearchResult> search(String text, int pageNumber) {
		
		PageRequest request = PageRequest.of(pageNumber-1, pageSize);//, Sort.Direction.DESC, "added");
		
		//profileDao.findByInterestsName(text).stream().forEach(System.out::println);
		Page<Profile> results = null;
		
		if(text.trim().length() == 0) {
			results = profileDao.findAll(request);
		}
		else {
			results = profileDao.findByInterestsNameContainingIgnoreCase(text, request); 
		}
		
		//Page<Profile> results = profileDao.findByInterestsNameContainingIgnoreCase(text, request);  //.stream().map(SearchResult::new).collect(Collectors.toList());
		/*
		Converter<Profile, SearchResult> converter = new Converter<Profile, SearchResult>() {				
			public SearchResult convert(Profile profile) {				
				return new SearchResult(profile);
			}			
		};
		*/
		
		return results.map(SearchResult::new);
		//return profileDao.findByInterestsNameContainingIgnoreCase(text, request).map(SearchResult::new);
	}
}
