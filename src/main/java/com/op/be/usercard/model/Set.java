package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "`set`")
public class Set {

	@Id
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private Long nCard;
	
	@Column
	private Long nParallel;

	public Set() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getnCard()
	{
		return nCard;
	}

	public void setnCard(Long nCard)
	{
		this.nCard = nCard;
	}

	public Long getnParallel()
	{
		return nParallel;
	}

	public void setnParallel(Long nParallel)
	{
		this.nParallel = nParallel;
	}

	@Override
	public String toString()
	{
		return "Set [id=" + id + ", name=" + name + ", nCard=" + nCard + ", nParallel=" + nParallel + "]";
	}

	

}
