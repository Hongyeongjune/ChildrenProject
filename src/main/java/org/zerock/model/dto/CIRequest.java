package org.zerock.model.dto;

import org.zerock.model.entity.UserCI;

import lombok.Getter;

@Getter
public class CIRequest {

	private String name;
	private String age;
	private String sex;
	private String leftStart;
	private String rightStart;
	private String leftDate;
	private String rightDate;
	private String leftDevice;
	private String rightDevice;
	private String type;
	private int number;
	private String answer;
	private String choice;
	private Long score;
	private String incorrect;
	private Long timer;
	
	public UserCI ToEntity() {
		UserCI user = new UserCI();
		user.setName(this.name);
		user.setAge(this.age);
		user.setSex(this.sex);
		user.setLeftStart(this.leftStart);
		user.setRightStart(this.rightStart);
		user.setLeftDate(this.leftDate);
		user.setRightDate(this.rightDate);
		user.setLeftDevice(this.leftDevice);
		user.setRightDevice(this.rightDevice);
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
