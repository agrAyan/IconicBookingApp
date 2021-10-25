package com.iconicsbooking.service;

import java.util.List;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Workers;
import com.iconicsbooking.model.WorkersPartialUpdate;

public interface IWorkerService {

	Workers addWorker(Workers worker);
	Workers updateWorker(Workers worker);
	Workers getByWorkerId(int workerId);
	List<Workers> getAllWorkers();
	String deleteWorker(int workerId);
	List<Workers> getByWorkerStatus(Availability status);
	List<Workers> getByJobType(String jobType);
	
	
}
