package com.op.be.usercard.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Deck;

@Repository("deckRepository")
public interface DeckRepository extends JpaRepository<Deck, Long> {

	@Query("SELECT c,d,sum(uc.qty) "
			+ "FROM Card c LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.Set s ON s.id = c.setId "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = 'gambero' "
			+ "INNER JOIN Deck d ON u.id =  d.userId AND "
			+ "d.cardList like CONCAT('%',s.cardCod,'-',c.number,'%') "
			+ "LEFT JOIN UserCard uc  ON "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "WHERE c.setId = 1  AND c.id in (13,14,15,16,17) "
			+ "GROUP BY c.id,d.id "
			+ "ORDER BY  c.number, cd.id ")
	List<Object> findDeckUser();

	
	
}
