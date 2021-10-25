package com.iconicsbooking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iconicsbooking.exception.WorkerNotFoundException;
import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Workers;
import com.iconicsbooking.model.WorkersPartialUpdate;
import com.iconicsbooking.repository.IWorkersRepository;
@Service
@Transactional
public class WorkerServiceImpl implements IWorkerService {

	String availibile= Availability.AVAILABLE.toString();
	String notAvialble= Availability.NOT_AVAILABLE.toString();
	@Autowired
	IWorkersRepository workerRepository;
	
	
	//Save the Worker Details
	@Override
	public Workers addWorker(Workers worker) {
		// TODO Auto-generated method stub
		Workers workerSave= workerRepository.save(worker);
		if(workerSave== null)
			throw new WorkerNotFoundException("cannot added");
		return workerSave;
	}

	@Override
	public Workers updateWorker(Workers worker) {
		Workers workerUpdate= workerRepository.save(worker);
		if(workerUpdate== null)
			throw new WorkerNotFoundException("cannot updated");
		return workerUpdate;
	}

	@Override
	public Workers getByWorkerId(int workerId) {
		Workers workerById= workerRepository.findById(workerId).get();
		if(workerById== null)
			throw new WorkerNotFoundException("No Id Found");
		return workerById;
	}

	@Override
	public List<Workers> getAllWorkers() {
		List<Workers> allWorkers= workerRepository.findAll();
		if(allWorkers.isEmpty()) 
			throw new WorkerNotFoundException("no worker Found");
		return allWorkers;
	}

	@Override
	public String deleteWorker(int workerId) {
		Workers worker= getByWorkerId(workerId);
		if(worker==null)
			throw new WorkerNotFoundException("no workerId Foundd.. try again");
		 workerRepository.deleteById(workerId);
		 return "deleted successfully";
	}

	@Override
	public List<Workers> getByWorkerStatus(Availability status) {
		List<Workers> allWorkerStatus= workerRepository.findByWorkerStatus(status);
		if(allWorkerStatus.isEmpty()) 
			throw new WorkerNotFoundException("no worker Found");
		return allWorkerStatus;
	}

	@Override
	public List<Workers> getByJobType(String jobType) {
		List<Workers> allWorkerJobType= workerRepository.findByJobType(jobType);
		if(allWorkerJobType.isEmpty()) 
			throw new WorkerNotFoundException("no worker Found");
		return allWorkerJobType;
	}


}
