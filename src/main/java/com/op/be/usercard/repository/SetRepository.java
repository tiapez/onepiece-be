package com.op.be.usercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Set;

@Repository("setRepository")
public interface SetRepository extends JpaRepository<Set, Long> {

	@Query(nativeQuery = true, value = "select s.* from `set` s order by s.id")
	List<Set> findSetList();
	
	@Query(nativeQuery = true, value = "select s.* from `set` s "
			+ "where s.id IN (select fs.set_id from format_set fs where format = :format) "
			+ "order by s.id")
	List<Set> findDeckSetList(@Param("format") String format);
}
