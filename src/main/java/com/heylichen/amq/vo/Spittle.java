package com.heylichen.amq.vo;

import java.io.Serializable;

public class Spittle implements Serializable {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Spittle [name=" + name + ", age=" + age + "]";
	}

}
