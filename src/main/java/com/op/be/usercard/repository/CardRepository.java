package com.op.be.usercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.op.be.usercard.model.Card;

@Repository("cardRepository")
public interface CardRepository extends JpaRepository<Card, Long> {

	@Query(" SELECT c " + 
	"FROM Card c " + 
	"WHERE c.setId = :set  ")
	List<Card> findSetCard(@RequestParam("set") String set );
	
	@Query(" SELECT c " + 
	"FROM Card c " + 
	"WHERE c.cardType = 'Leader'  "+ 
	"ORDER BY c.setId,c.number  ")
	List<Card> findAllLeader();
	
	@Query(" SELECT c " + 
	"FROM Card c " + 
	"ORDER BY c.setId,c.number  ")
	List<Card> findAllCard();

}
