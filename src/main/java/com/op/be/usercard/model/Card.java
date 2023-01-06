package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card 
{

	@Id@
	GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	@Column
	private String number;
	@Column
	private String rarity;
	@Column
	private String setId;
	@Column
	private String name;
	@Column
	private String color;
	@Column
	private String cardType;
	@Column
	private String type;
	@Column
	private String effect;
	@Column
	private Long cost;
	@Column
	private Long power;
	@Column
	private Long counter;
	@Column
	private String role;

	public Card(Long id, String number, String rarity, String setId, String name, String color, String cardType,
			String type, String effect, Long cost, Long power, Long counter, String role) {
		super();
		this.id = id;
		this.number = number;
		this.rarity = rarity;
		this.setId = setId;
		this.name = name;
		this.color = color;
		this.cardType = cardType;
		this.type = type;
		this.effect = effect;
		this.cost = cost;
		this.power = power;
		this.counter = counter;
		this.role = role;
	}

	public Card() {
		super();
	}
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(Long power) {
		this.power = power;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", rarity=" + rarity + ", setId=" + setId + ", name=" + name
				+ ", color=" + color + ", cardType=" + cardType + ", type=" + type + ", effect=" + effect + ", cost="
				+ cost + ", power=" + power + ", counter=" + counter + ", role=" + role + "]";
	}

}
