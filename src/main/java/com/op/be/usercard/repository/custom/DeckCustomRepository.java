package com.op.be.usercard.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.dto.DeckCardRow;
import com.op.be.usercard.model.dto.UserCardDTO;

@Repository("deckCustomRepository")
public interface DeckCustomRepository extends JpaRepository<Deck, Long> {

	@Query("SELECT new com.op.be.usercard.model.dto.DeckCardRow(c,d,COALESCE(sum(uc.qty),0),COALESCE(dc.qty,1),COALESCE(cl.qtyMax,4)) "
			+ "FROM Deck d INNER JOIN com.op.be.usercard.model.User u "
			+ "ON u.nick = :nick  AND u.id = d.userId "
			+ "LEFT JOIN DeckCard dc ON dc.deckId = d.id "
			+ "INNER JOIN Card c ON c.id = dc.cardId OR c.id = d.leader "
			+ "LEFT JOIN CardLimit cl ON cl.cardId = c.id AND cl.format = d.format "
			+ "LEFT JOIN UserCard uc ON uc.cardId = c.id AND uc.userId = u.id "
			+ "LEFT JOIN CardDetails cd ON cd.id = uc.detailsId "
			+ "WHERE cd.codCondition <= d.cond OR cd.codCondition is null "
			+ "GROUP BY c.id,d.id "
			+ "ORDER BY  d.id,c.setId,c.number ")
	List<DeckCardRow> findDeckUser(@Param("nick") String nick);

	@Query("SELECT new com.op.be.usercard.model.dto.UserCardDTO(c,uc,cd,COALESCE(cl.qtyMax,4)) "
			+ "FROM Card c "
			+ "LEFT JOIN CardLimit cl ON cl.cardId = c.id AND cl.format = :lang "
			+ "LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user "
			+ "LEFT JOIN UserCard uc  ON  "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "WHERE (c.color = :color1 OR c.color = :color2) AND cd.language = :lang and cd.codCondition <= :codCond AND c.cardType != 'Leader' "
			+ "ORDER BY  c.setId,c.number, cd.id ")
	List<UserCardDTO> findUserCardDetailsByDeck(@Param("lang") String lang,@Param("color1") String color1,
			@Param("color2") String color2,@Param("codCond") int codCond, @Param("user") String user);

	
	
}
