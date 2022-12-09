package com.op.be.usercard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.op.be.usercard.model.UserCard;

@Repository("CardUserRepository")
public interface UserCardRepository extends JpaRepository<UserCard, Long> {

//	@Query("SELECT uc "
//			+ "FROM UserCard uc "
//			+ "WHERE uc.cardId = :cardId AND uc.userId = :userId "
//			+ "AND uc.detailsId = :detailsId ")
//	Optional<UserCard> findCardUser(@Param("cardId") int cardId,
//			@Param("userId") int userId,
//			@Param("detailsId") int detailsId);

	@Query("SELECT uc " + "FROM UserCard uc "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user AND u.id = uc.userId "
			+ "WHERE uc.cardId = :cardId " + "AND uc.detailsId = :detailsId ")
	Optional<UserCard> findCardUserDetails(@Param("cardId") int cardId, @Param("user") String user,
			@Param("detailsId") int detailsId);

	@Query("SELECT uc " + "FROM UserCard uc "
			+ "INNER JOIN com.op.be.usercard.model.User u ON u.nick = :user AND u.id = uc.userId "
			+ "INNER JOIN CardDetails cd ON cd.language = u.language "
			+ "AND cd.cod_condition = u.condition AND uc.detailsId = cd.id " + "WHERE uc.cardId = :cardId ")
	Optional<UserCard> findCardUserClassic(@Param("cardId") int cardId, @Param("user") String user);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) " + "SELECT u.id,:cardId,cd.id,:qty "
			+ "from com.op.be.usercard.model.User u INNER JOIN CardDetails cd "
			+ "ON u.language = cd.language AND u.nick = :user " + "AND u.condition = cd.cod_condition "
			+ "WHERE u.nick = :user ")
	void insertUserCardClassic(@Param("cardId") int cardId, @Param("user") String user, @Param("qty") int qty);

	@Transactional
	@Modifying
	@Query("INSERT INTO UserCard (userId,cardId,detailsId,qty) " + "SELECT u.id,:cardId,:detId,:qty "
			+ "from com.op.be.usercard.model.User u " + "WHERE u.nick = :user ")
	void insertUserCardDetails(@Param("cardId") int cardId, @Param("user") String user, @Param("detId") int detId,
			@Param("qty") int qty);

}
