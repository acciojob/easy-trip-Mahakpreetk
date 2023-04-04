package com.driver.Services;

import com.driver.Repository.AirportRepository;import com.driver.model.Airport;import com.driver.model.City;import com.driver.model.Flight;import com.driver.model.Passenger;import org.springframework.stereotype.Service;import java.util.Date;

@Service
public class AirportServices {
    AirportRepository airportRepository = new AirportRepository();

    public void addAirport(Airport airport) {
        airportRepository.addAirport(airport);
    }

    public String getLargestAirportName() {
        return airportRepository.getLargestAirportName();
    }

    public void addPassenger(Passenger passenger) {
        airportRepository.addPassenger(passenger);
    }

    public void addFlight(Flight flight) {
        airportRepository.addFlight(flight);
    }

    public String bookATicket(int flightId, int passengerId) {
        return airportRepository.bookATicket(flightId, passengerId);
    }

    public String cancelATicket(int flightId, int passengerId) {
        return airportRepository.cancelATicket(flightId, passengerId);
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        return airportRepository.getShortestDurationOfPossibleBetweenTwoCities(fromCity, toCity);
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        return airportRepository.getNumberOfPeopleOn(date, airportName);
    }

    public int calculateFlightFare(int flightId) {
        return airportRepository.calculateFlightFare(flightId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(int passengerId) {
        return airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }

    public String getAirportNameFromFlightId(int flightId) {
        return airportRepository.getAirportNameFromFlightId(flightId);
    }

    public int calculateRevenueOfAFlight(int flightId)
    {
        return airportRepository.calculateRevenueOfAFlight(flightId);
    }

}