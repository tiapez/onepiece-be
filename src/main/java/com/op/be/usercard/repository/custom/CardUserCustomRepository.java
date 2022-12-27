package com.op.be.usercard.repository.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Card;
import com.op.be.usercard.model.dto.UserCardDTO;

@Repository("cardUserCustomRepository")
public interface CardUserCustomRepository extends JpaRepository<Card, Long> {

	@Query("SELECT new com.op.be.usercard.model.dto.UserCardDTO(c,uc,cd) "
			+ "FROM Card c LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user "
			+ "LEFT JOIN UserCard uc  ON  "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "WHERE c.setId = :set  "
			+ "ORDER BY  c.number, cd.id ")
	List<UserCardDTO> findUserCardDetailsBySet(@Param("set") String set, @Param("user") String user);

	@Query("SELECT new com.op.be.usercard.model.dto.UserCardDTO(c,uc,cd) "
			+ "FROM Card c LEFT JOIN CardDetails cd ON 1=1 "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user "
			+ "LEFT JOIN UserCard uc  ON  "
			+ "(uc.detailsId is null and uc.userId is null and uc.cardId is null) "
			+ "OR ( uc.detailsId = cd.id and uc.userId = u.id and uc.cardId = c.id) "
			+ "WHERE c.setId = :set  "
			+ "AND cd.language = u.language AND cd.codCondition = u.condition " 
			+ "ORDER BY  c.number ")
	List<UserCardDTO> findUserCardClassicBySet(@Param("set") String set, @Param("user") String user);


	
}
