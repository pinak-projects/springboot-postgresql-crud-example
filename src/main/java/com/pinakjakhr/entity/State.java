package com.pinakjakhr.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State implements Serializable {

	@NotBlank
	private String code;
	
	@NotBlank
	private String name;
}
