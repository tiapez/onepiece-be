package com.op.be.usercard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(FormatSetId.class)
public class FormatSet implements Serializable
{
	@Id
	@Column
	String format;
	@Id
	@Column
	String setId;

	public String getFormat()
	{
		return format;
	}

	public void setFormat(String format)
	{
		this.format = format;
	}

	public String getSetId()
	{
		return setId;
	}

	public void setSetId(String setId)
	{
		this.setId = setId;
	}

	@Override
	public String toString()
	{
		return "FormatSet [format=" + format + ", setId=" + setId + "]";
	}
	
	
}
