package com.iconicsbooking.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iconicsbooking.exception.CompanyNotFoundException;
import com.iconicsbooking.exception.EventsNotFoundException;
import com.iconicsbooking.exception.IdNotFoundException;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.IconicBooking;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.repository.IconicBookingRepository;

@Service
public class IconicBookingServiceImpl implements IconicBookingService {

	/*
	 * here we are injecting instance of IconicBookingRepository interface into
	 * IconicServiceImpl so that we can use IconicRepository class for accessing
	 * instance variables and methods
	 */
	@Autowired
	IconicBookingRepository iconicBookingRepository;

	// Autowiring the RestTemplate object that is used to connect with other
	// microservices
	@Autowired
	RestTemplate restTemplate;

	private final String BASEURL = "http://localhost:8082/events-api";

	// this method is used to add a company
	@Override
	/**
	 * @author AyanAgrawal
	 */
	public IconicBooking addComapny(IconicBooking company) {
		IconicBooking iconicBooking = iconicBookingRepository.save(company);
		if (iconicBooking == null)
			throw new CompanyNotFoundException("cannot add company.. try again");
		return iconicBooking;
	}

	// this method is used to update the company Details
	@Override
	public IconicBooking updateCompany(IconicBooking company) {
		IconicBooking iconicBooking = iconicBookingRepository.save(company);
		if (iconicBooking == null)
			throw new CompanyNotFoundException("cannot update company.. try again");
		return iconicBooking;
	}

	// this method is used to delete a company
	@Override
	public void deleteCompany(int companyId) {
		iconicBookingRepository.deleteById(companyId);
	}

	// this method is used to get the details of all the company
	@Override
	public List<IconicBooking> getAll() {
		List<IconicBooking> companyList = iconicBookingRepository.findAll();
		if (companyList.isEmpty())
			throw new CompanyNotFoundException("no company found");
		return companyList;
	}

	// this method is used to get the company details by Id
	@Override
	public IconicBooking getById(int companyId) throws IdNotFoundException {
		IconicBooking bookingById = iconicBookingRepository.findById(companyId).get();
		if (bookingById == null)
			throw new IdNotFoundException("no company id found");
		return bookingById;
	}

// this method is used to search the company Details By CompanyName
	@Override
	public List<IconicBooking> getByCompanyName(String companyName) throws CompanyNotFoundException {
		List<IconicBooking> companyListByName = iconicBookingRepository.findByCompanyName(companyName);
		if (companyListByName.isEmpty())
			throw new CompanyNotFoundException("no company name found");
		return companyListByName;

	}

// this method is used to search the company details by ownerName
	@Override
	public List<IconicBooking> getByOwnerName(String ownerName) throws CompanyNotFoundException {
		List<IconicBooking> companyOwnerName = iconicBookingRepository.findByOwnerName(ownerName);
		if (companyOwnerName.isEmpty())
			throw new CompanyNotFoundException("No owner name found");
		return companyOwnerName;
	}

// this method is used to search the company By Rating 
	@Override
	public List<IconicBooking> getByRating(double rating) throws CompanyNotFoundException {
		List<IconicBooking> companyByRating = iconicBookingRepository.findByRatingLessThan(rating);
		if (companyByRating.isEmpty())
			throw new CompanyNotFoundException("rating no found");
		return companyByRating;
	}

// this method is used to add the new events by restTemplate 
	@Override
	@JsonProperty
	public Events addEvent(Events events) {
		System.out.println(events);
		String url = BASEURL + "/events";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Events> requestEntity = new HttpEntity<>(events, requestHeaders);
		return restTemplate.exchange(url, HttpMethod.POST, requestEntity, Events.class).getBody();

	}

// this method is used to get all the events
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getAllEvents() {
		String url = BASEURL + "/events";

		return restTemplate.getForEntity(url, List.class).getBody();
	}

// this method is used to get the events By Id
	@Override
	public Events getByEventId(int eventId) throws EventsNotFoundException {
		String url = BASEURL + "/events/eventId/" + eventId;
		return restTemplate.getForObject(url, Events.class);
	}

// this method is used to get the events By eventProviderName
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByEventProvider(String eventProvider) {
		String url = BASEURL + "/events/eventProvider/" + eventProvider;
		return restTemplate.getForEntity(url, List.class).getBody();

	}

// this method is used to get the events By EventName
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByEventName(String eventName) throws EventsNotFoundException {
		String url = BASEURL + "/events/eventName/" + eventName;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

// this method is used to get the events By start Date
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByStartDate(String startDate) throws EventsNotFoundException {
		String url = BASEURL + "/events/startDate/" + startDate;
		return restTemplate.getForEntity(url, List.class).getBody();

	}

// this method is used to get the events By EndDate
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByEndDate(String endDate) {
		String url = BASEURL + "/events/endDate/" + endDate;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

// this method is used to get the events By Status
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByStatus(String status) {
		String url = BASEURL + "/events/status/" + status;
		return restTemplate.getForEntity(url, List.class).getBody();
	}

// this method is used to get the events By price less than
	@SuppressWarnings("unchecked")
	@Override
	public List<Events> getByPrice(double price) {
		String url = BASEURL + "/events/price/" + price;
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> eventByPrice = restTemplate.getForEntity(url, List.class);
		return eventByPrice.getBody();
	}

// this method is used to get the events By Priority
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Events> getByPriority(String priority) {
		String url = BASEURL + "/events/priority/" + priority;
		ResponseEntity<List> eventByPriority = restTemplate.getForEntity(url, List.class);
		return eventByPriority.getBody();
	}

// this method is used to company to the events 
	@SuppressWarnings("unused")
	@Override
	public String assignEvent(int companyId, int eventId) {
		IconicBooking iconicBooking = getById(companyId);
		iconicBooking.setCompanyId(iconicBooking.getCompanyId());

		String urlForEventById = BASEURL + "/events/eventId/" + eventId;
		Events eventById = restTemplate.getForEntity(urlForEventById, Events.class).getBody();
		eventById.setStatus(Status.IN_PROGRESS);
		eventById.setIconicBooking(iconicBooking);
		String url = BASEURL + "/events";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Events> requestEntity = new HttpEntity<>(eventById, requestHeaders);
		ResponseEntity<Events> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Events.class);

		return "events assigned";

	}

// this method is used to free the events
	@SuppressWarnings("unused")
	@Override
	public String freeEvent(int eventId) throws EventsNotFoundException {
		String urlForEventById = BASEURL + "/events/eventId/" + eventId;
		Events eventById = restTemplate.getForEntity(urlForEventById, Events.class).getBody();
		eventById.setStatus(Status.NOT_STARTED);

		eventById.setIconicBooking(null);
		String url = BASEURL + "/events";

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Events> requestEntity = new HttpEntity<>(eventById, requestHeaders);
		ResponseEntity<Events> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Events.class);
		return "free events";
	}

}
