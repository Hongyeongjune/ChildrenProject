package org.zerock.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserNH {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String age;
	private String sex;
	private String type;
	private int number;
	private String answer;
	private String choice;
	private Long score;
	private String incorrect;
	private Long timer;

}