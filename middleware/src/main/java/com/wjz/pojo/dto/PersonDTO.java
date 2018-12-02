package com.wjz.pojo.dto;

import java.io.Serializable;

public class PersonDTO implements Serializable {

	private static final long serialVersionUID = 4395948034932429624L;

	private String id;
	
	private String name;

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

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + "]";
	}
	
}
