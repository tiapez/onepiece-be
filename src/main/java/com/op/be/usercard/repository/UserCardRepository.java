package com.op.be.usercard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.UserCardId;


@Repository("CardUserRepository")
public interface UserCardRepository extends JpaRepository<UserCard, UserCardId>
{

	@Query("SELECT uc " + "FROM UserCard uc "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user AND u.id = uc.userId "
			+ "WHERE uc.cardId = :cardId " + "AND uc.detailsId = :detailsId ")
	Optional<UserCard> findCardUserDetails(@Param("cardId") Long cardId, @Param("user") String user,
			@Param("detailsId") Long id);

	@Query("SELECT uc " + "FROM UserCard uc "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user AND u.id = uc.userId "
			+ "INNER JOIN CardDetails cd ON cd.language = u.language "
			+ "AND cd.codCondition = u.condition AND uc.detailsId = cd.id "
			+ "WHERE uc.cardId = :cardId ")
	Optional<UserCard> findCardUserClassic(@Param("cardId") Long cardId, @Param("user") String user);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) " + "SELECT u.id,:cardId,cd.id,:qty "
			+ "from com.op.be.usercard.model.User u INNER JOIN CardDetails cd "
			+ "ON u.language = cd.language AND u.nick = :user " + "AND u.condition = cd.codCondition "
			+ "WHERE u.nick = :user ")
	void insertUserCardClassic(@Param("cardId") Long cardId, @Param("user") String user, @Param("qty") int qty);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) " + "SELECT u.id,:cardId,:detId,:qty "
			+ "from com.op.be.usercard.model.User u " + "WHERE u.nick = :user ")
	void insertUserCardDetails(@Param("cardId") Long cardId, @Param("user") String user, @Param("detId") Long detId,
			@Param("qty") int qty);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) "
			+ "SELECT u.id,c.id,cd.id,:qty " + "from com.op.be.usercard.model.User u INNER JOIN CardDetails cd "
			+ "ON u.language = cd.language AND u.nick = :nick AND u.condition = cd.codCondition "
			+ "INNER JOIN Card c ON c.number = :cardNum AND c.setId = :cardSet ")
	void importUserCardClassic(@Param("cardNum") String cardNum, @Param("cardSet") String cardSet,
			@Param("nick") String nick, @Param("qty") int qty);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) "
			+ "SELECT u.id,:cardId,:detId,:qty " + "from com.op.be.usercard.model.User u " + "WHERE u.nick = :user ")
	void importUserCardDetails(@Param("cardId") Long cardId, @Param("user") String user, @Param("detId") Long detId,
			@Param("qty") int qty);

	@Query("SELECT uc " + "FROM UserCard uc "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :nick AND u.id = uc.userId "
			+ "INNER JOIN CardDetails cd ON cd.language = u.language "
			+ "AND cd.codCondition = u.condition AND uc.detailsId = cd.id "
			+ "INNER JOIN Card c ON c.number = :cardNum AND c.setId = :cardSet AND uc.cardId = c.id ")
	Optional<UserCard> findCardUserClassic2(@Param("cardNum") String cardNum, @Param("cardSet") String cardSet,
			@Param("nick") String nick);

}
