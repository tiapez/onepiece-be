package com.op.be.usercard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.op.be.usercard.model.DeckCard;
import com.op.be.usercard.model.DeckCardId;


@Repository("deckCardRepository")
public interface DeckCardRepository extends CrudRepository<DeckCard, DeckCardId>{

	@Modifying
    @Transactional 
	@Query("DELETE "
			+ "FROM DeckCard "
			+ "WHERE deckId = :id ")
	void deleteByDeckId(@Param("id") int deckId);
}
