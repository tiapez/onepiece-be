package com.op.be.usercard.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.dto.DeckDTO;

@Repository("deckRepository")
public interface DeckRepository extends JpaRepository<Deck, Long> {

	@Query("SELECT new com.op.be.usercard.model.dto.DeckDTO(c,d,COALESCE(sum(uc.qty),0)) "
			+ "FROM Card c LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = 'gambero' "
			+ "INNER JOIN Deck d ON u.id =  d.userId AND "
			+ "d.cardList like CONCAT('%',c.setId,'-',c.number,'x%') "
			+ "LEFT JOIN UserCard uc  ON "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "GROUP BY c.id,d.id "
			+ "ORDER BY  d.id,c.number ")
	List<DeckDTO> findDeckUser();

	
	
}
