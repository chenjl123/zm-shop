package com.zm.sys.bean;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
	
	@NotBlank(message = "id不能为空")
	private String id;
	
	@Length(min=5, max=10, message="name只能5-10位")
	private String name;
	
	@Range(min=10, max=30, message="年龄只能10-30岁")
	private Integer age;
	
	@Email(message="格式不是邮箱" )
	private String email;
	
	@Pattern(regexp="^/d$")
	private String mobile;
	
	@AssertTrue(message = "用户必须有效")
	private boolean valid;
	
}
