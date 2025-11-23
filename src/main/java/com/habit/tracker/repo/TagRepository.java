package com.habit.tracker.repo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.habit.tracker.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	public Optional<Tag> findByName(String name);

	/*
	 * SELECT * FROM tag  WHERE name IN (?, ?, ?, ...);
	 */
	List<Tag> findAllByNameIn(Collection<String> names);

}
