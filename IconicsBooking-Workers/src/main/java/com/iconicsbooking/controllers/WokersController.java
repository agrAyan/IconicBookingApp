package com.iconicsbooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Workers;
import com.iconicsbooking.service.IWorkerService;

@RestController
@RequestMapping("/worker-api")
public class WokersController {

	@Autowired
	IWorkerService workerService;
	
	@PostMapping("/worker")
	ResponseEntity<Workers> addWorker(@RequestBody Workers worker) {
		Workers workerAdd= workerService.addWorker(worker);	
		ResponseEntity<Workers> responseEntity= new ResponseEntity<Workers>(workerAdd,HttpStatus.OK);
		return responseEntity;
	}
	@PutMapping("/worker")
	ResponseEntity<Workers> updateWorker(@RequestBody Workers worker) {
		Workers message= workerService.updateWorker(worker);
	//	ResponseEntity<String> responseEntity= new ResponseEntity<>(message,HttpStatus.OK);
		ResponseEntity<Workers> responseEntity= new ResponseEntity<>(message,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("worker/workerId/{workerId}")
	ResponseEntity<Workers>  getByWorkerId(@PathVariable("workerId") int workerId) {
		Workers worker= workerService.getByWorkerId(workerId);
		ResponseEntity<Workers> responseEntity= new ResponseEntity<>(worker,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/worker")
	ResponseEntity<List<Workers>> getAllWorkers(){
		List<Workers> allWorkers=  workerService.getAllWorkers();
		ResponseEntity<List<Workers>> responseEntity= new ResponseEntity<>(allWorkers,HttpStatus.OK);
		return responseEntity;
	}
	@DeleteMapping("/worker/workerId/{workerId}")
	ResponseEntity<String> deleteWorker(@PathVariable("workerId") int workerId) {
		String message= workerService.deleteWorker(workerId);
		ResponseEntity<String> responseEntity= new ResponseEntity<>(message,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/worker/status/{status}")
	ResponseEntity<List<Workers>> getByWorkerStatus(@PathVariable("status") String status){
		Availability statusValue= Availability.valueOf(status);
		List<Workers> workerByStatus= workerService.getByWorkerStatus(statusValue);
		ResponseEntity<List<Workers>> responseEntity= new ResponseEntity<>(workerByStatus,HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/worker/jobType/{jobType}")
	ResponseEntity<List<Workers>> getByJobType(@PathVariable("jobType") String jobType){
		List<Workers> workerByJobType= workerService.getByJobType(jobType);
		ResponseEntity<List<Workers>> responseEntity= new ResponseEntity<>(workerByJobType,HttpStatus.OK);
		return responseEntity;
	}

	
}
