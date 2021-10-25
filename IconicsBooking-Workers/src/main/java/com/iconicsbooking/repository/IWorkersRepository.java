package com.iconicsbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Workers;
import com.iconicsbooking.model.WorkersPartialUpdate;

@Repository
public interface IWorkersRepository extends JpaRepository<Workers, Integer> {

	List<Workers> findByWorkerStatus(Availability status);
	List<Workers> findByJobType(String jobType);

	
}
