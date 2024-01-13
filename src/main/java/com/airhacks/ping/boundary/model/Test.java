package com.airhacks.ping.boundary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Test {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String testExample;
	
	public String getTestExample() {
		return testExample;
	}
	
	public void setTestExample(String testExample) {
		this.testExample = testExample;
	}
	
	
	
}
