package com.iconicsbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iconicsbooking.exception.CompanyNotFoundException;
import com.iconicsbooking.exception.EventsNotFoundException;
import com.iconicsbooking.exception.IdNotFoundException;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.IconicBooking;
import com.iconicsbooking.service.IconicBookingService;

@RestController
@RequestMapping("/company-api")
/**
 * 
 * @author AyanAgrawal
 *
 */
public class IconicsController {
	@Autowired
	IconicBookingService iBookingService;

	// this url is called when the user wants to add a company
	@PostMapping("/company")
	ResponseEntity<IconicBooking> addCompany(@RequestBody IconicBooking iconicBooking) {
		return ResponseEntity.status(HttpStatus.CREATED).body(iBookingService.addComapny(iconicBooking));
	}

	// this url is called when the user wants to update the company
	@PutMapping("/company")
	ResponseEntity<IconicBooking> updateCompany(@RequestBody IconicBooking iconicBooking) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.updateCompany(iconicBooking));
	}

	// this url is called when the user wants to delete a company
	@DeleteMapping("/company/{companyId}")
	void deleteCompany(@PathVariable("companyId") int companyId) {
		iBookingService.deleteCompany(companyId);
	}

	// this url is called when the user wants to get all details of the company
	@GetMapping("/company")
	ResponseEntity<List<IconicBooking>> getAll() {
		return ResponseEntity.status(HttpStatus.CREATED).body(iBookingService.getAll());
	}

	// this url is called when the user wants to get the company by id
	@GetMapping("/company/companyId/{companyId}")
	ResponseEntity<IconicBooking> getById(@PathVariable("companyId") int companyId) throws IdNotFoundException {
		IconicBooking comapanyById = iBookingService.getById(companyId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get one event by Id");
		headers.add("info", "Returning one event");
		ResponseEntity<IconicBooking> responseCompany = new ResponseEntity<IconicBooking>(comapanyById, headers,
				HttpStatus.OK);
		return responseCompany;
	}

	// this url is called when the user wants to get the company by companyName
	@GetMapping("/company/companyName/{companyName}")
	ResponseEntity<List<IconicBooking>> getByCompanyName(@PathVariable("companyName") String companyName) {
		List<IconicBooking> companyByname = iBookingService.getByCompanyName(companyName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get eventList By company name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(companyByname);
	}

	// this url is called when the user wants to get the company by rating
	@GetMapping("/company/rating/{rating}")
	ResponseEntity<List<IconicBooking>> getByRating(@PathVariable("rating") double rating) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get eventList By company rating");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(iBookingService.getByRating(rating));
	}

	// this url is called when the user wants to get the company by ownerName
	@GetMapping("/company/ownerName/{ownerName}")
	ResponseEntity<List<IconicBooking>> getByOwnerName(@PathVariable("ownerName") String ownerName)
			throws CompanyNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get eventList By company owner name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(iBookingService.getByOwnerName(ownerName));
	}

	// this url is called when the user wants to add the events
	@JsonProperty
	@PostMapping("/company/events")
	public ResponseEntity<Events> addEvent(@RequestBody Events events) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.addEvent(events));
	}

	// this url is called when the user wants to get all the events Details
	@GetMapping("/company/events")
	public ResponseEntity<List<Events>> getAllEvents() {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getAllEvents());
	}

	// this url is called when the user wants to get the events by eventId
	@GetMapping("/company/events/eventId/{eventId}")
	ResponseEntity<Events> getByEventId(@PathVariable("eventId") int eventId) throws EventsNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventId(eventId));
	}

	// this url is called when the user wants to get the events by eventProviderName
	@GetMapping("/company/events/eventProvider/{eventProvider}")
	ResponseEntity<List<Events>> getByEventProvider(@PathVariable("eventProvider") String eventProvider) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventProvider(eventProvider));
	}

	// this url is called when the user wants to get the events by eventName
	@GetMapping("/company/events/eventName/{eventName}")
	ResponseEntity<List<Events>> getByEventName(@PathVariable("eventName") String eventName) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventName(eventName));
	}

	// this url is called when the user wants to get the events by startdate
	@GetMapping("/company/events/startDate/{startDate}")
	ResponseEntity<List<Events>> getByStartDate(@PathVariable("startDate") String startDate) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByStartDate(startDate));
	}

	// this url is called when the user wants to get the events by endDate
	@GetMapping("/company/events/endDate/{endDate}")
	ResponseEntity<List<Events>> getByEndDate(@PathVariable("endDate") String endDate) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEndDate(endDate));
	}

	// this url is called when the user wants to get the events by status
	@GetMapping("/company/events/status/{status}")
	ResponseEntity<List<Events>> getByStatus(@PathVariable("status") String status) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByStatus(status));
	}

	// this url is called when the user wants to get the events by price
	@GetMapping("/company/events/price/{price}")
	ResponseEntity<List<Events>> getByPrice(@PathVariable("price") double price) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByPrice(price));
	}

	// this url is called when the user wants to get the events by priority
	@GetMapping("/company/events/priority/{priority}")
	ResponseEntity<List<Events>> getByPriority(@PathVariable("priority") String priority) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByPriority(priority));
	}

	// this url is called when the user wants to assign a company to event
	@GetMapping("/company/events/assign/companyId/{companyId}/eventId/{eventId}")
	ResponseEntity<String> assignEvent(@PathVariable("companyId") int companyId, @PathVariable("eventId") int eventId) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.assignEvent(companyId, eventId));
	}

	// this url is called when the user wants to freeEvents
	@GetMapping("/company/events/freeEvent/eventId/{eventId}")
	ResponseEntity<String> freeEvent(@PathVariable("eventId") int eventId) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.freeEvent(eventId));
	}

}
