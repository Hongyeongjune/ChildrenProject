package org.zerock.model.dto;

import org.zerock.model.entity.UserNH;

import lombok.Getter;
import lombok.Setter;

@Getter
public class NHRequest {

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
	
	public UserNH ToEntity() {
		UserNH user = new UserNH();
		user.setName(this.name);
		user.setAge(this.age);
		user.setSex(this.sex);
		user.setType(this.type);
		user.setNumber(this.number);
		user.setAnswer(this.answer);
		user.setChoice(this.choice);
		user.setScore(this.score);
		user.setIncorrect(this.incorrect);
		user.setTimer(this.timer);
		
		return user;
	}
	
}
