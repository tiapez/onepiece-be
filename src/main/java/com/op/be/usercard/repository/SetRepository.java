package com.op.be.usercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Set;

@Repository("setRepository")
public interface SetRepository extends JpaRepository<Set, Long> {

	@Query(nativeQuery = true, value = "select s.* from `set` s")
	List<Set> findSetList();
}
