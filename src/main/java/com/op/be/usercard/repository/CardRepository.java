package com.op.be.usercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Card;

@Repository("cardRepository")
public interface CardRepository extends JpaRepository<Card, Long> {

	@Query(value = " Select card.* " + "From card " + "where set_id = :set ", nativeQuery = true)
	List<Card> findSetCard(@Param("set") int set);
	
	@Query(" SELECT c " + 
	"FROM Card c " + 
	"WHERE c.cardType = 'Leader'  ")
	List<Card> findAllLeader();

}
