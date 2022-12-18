package com.op.be.usercard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "`set`")
public class Set {

	@Id
	@Column
	String id;
	@Column
	String name;
	@Column
	int list;

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

	public int getList() {
		return list;
	}

	public void setList(int list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Set [id=" + id + ", name=" + name + ", list=" + list + "]";
	}

}
