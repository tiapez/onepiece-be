package com.op.be.usercard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.op.be.usercard.model.Deck;

@Repository("deckRepository")
public interface DeckRepository extends CrudRepository<Deck, Long>{

}
