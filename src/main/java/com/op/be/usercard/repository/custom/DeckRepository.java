package com.op.be.usercard.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Deck;
import com.op.be.usercard.model.dto.DeckDTO;
import com.op.be.usercard.model.dto.UserCardDTO;

@Repository("deckRepository")
public interface DeckRepository extends JpaRepository<Deck, Long> {

	@Query("SELECT new com.op.be.usercard.model.dto.DeckDTO(c,d,COALESCE(sum(uc.qty),0),COALESCE(dc.qty,1)) "
			+ "FROM Deck d INNER JOIN com.op.be.usercard.model.User u "
			+ "ON u.nick = :nick  AND u.id = d.userId "
			+ "LEFT JOIN DeckCard dc ON dc.deckId = d.id "
			+ "INNER JOIN Card c ON c.id = dc.cardId OR c.id = d.leader "
			+ "LEFT JOIN UserCard uc ON uc.cardId = c.id AND uc.userId = u.id "
			+ "LEFT JOIN CardDetails cd ON cd.id = uc.detailsId "
			+ "WHERE cd.cod_condition <= d.cond OR cd.cod_condition is null "
			+ "GROUP BY c.id,d.id "
			+ "ORDER BY  d.id,c.setId,c.number ")
	List<DeckDTO> findDeckUser(@Param("nick") String nick);
	
	
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
	List<DeckDTO> findDeckUser2();

	@Query("SELECT new com.op.be.usercard.model.dto.UserCardDTO(c,uc,cd) "
			+ "FROM Card c LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user "
			+ "LEFT JOIN UserCard uc  ON  "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "WHERE c.color = :color AND cd.language = :lang and cd.cod_condition <= :codCond AND c.cardType != 'Leader' "
			+ "ORDER BY  c.setId,c.number, cd.id ")
	List<UserCardDTO> findUserCardDetailsByDeck(@Param("lang") String lang,@Param("color") String color,
			@Param("codCond") int codCond, @Param("user") String user);

	
	
}
