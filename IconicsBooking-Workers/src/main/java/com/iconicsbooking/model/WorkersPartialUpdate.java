package com.iconicsbooking.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WorkersPartialUpdate {
	
	private Integer workerId;
	//Task taskService;
	@Enumerated(EnumType.STRING)
	private Availability workerStatus;
}
