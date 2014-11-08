package com.test.guava.collections;

public class StudentEntity {

	private Integer id;
	private String name;
	private String gender;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public static StudentEntity build(Integer id, String name, String gender){
		StudentEntity se = new StudentEntity();
		se.setId(id);
		se.setName(name);
		se.setGender(gender);
		return se;
	}
	
}
