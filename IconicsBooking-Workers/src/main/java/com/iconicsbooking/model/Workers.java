package com.iconicsbooking.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class Workers {
	
	
	
	public Workers(String workerName, String contactNumber, Availability workerStatus, String jobType) {
		super();
		this.workerName = workerName;
		this.contactNumber = contactNumber;
		this.workerStatus = workerStatus;
		this.jobType = jobType;
	}
	@Id
	@GeneratedValue(generator = "worker_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="worker_gen",sequenceName = "worker_seq",allocationSize = 1,initialValue = 1)
	private Integer workerId;
	private String workerName;
	private String contactNumber;
	@Enumerated(EnumType.STRING)
	private Availability workerStatus;
	private String jobType;
	@ManyToOne
	@JoinColumn(name="task_id")
	//@JsonIgnore
	Task taskService;
	
	
	
}
