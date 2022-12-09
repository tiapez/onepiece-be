package com.op.be.usercard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(nativeQuery = true, value = "select username from user")
	List<String> getAllUsername();

	@Query(nativeQuery = true, value = "select mail from user")
	List<String> getAllMail();

	@Query(nativeQuery = true, value = "select nick from user")
	List<String> getAllNick();

	@Query(nativeQuery = true, value = "select user.* from user where username = :user AND password = :pass")
	Optional<User> findByUserPass(@Param("user") String user, @Param("pass") String pass);

	@Query(nativeQuery = true, value = "select user.* from user where nick = :nick")
	Optional<User> findByNick(@Param("nick") String nick);

}
