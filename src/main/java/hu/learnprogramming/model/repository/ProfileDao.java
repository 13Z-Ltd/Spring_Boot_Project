package hu.learnprogramming.model.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import hu.learnprogramming.model.entity.Profile;
import hu.learnprogramming.model.entity.SiteUser;

public interface ProfileDao extends CrudRepository<Profile, Long> {
	Profile findByUser(SiteUser user);

	//List<Profile> findByInterestsName(String text);
	List<Profile> findByInterestsNameContainingIgnoreCase(String text);
	Page<Profile> findByInterestsNameContainingIgnoreCase(String text, Pageable request);
	Page<Profile> findAll(Pageable request);
}
