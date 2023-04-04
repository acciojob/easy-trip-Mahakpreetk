package com.driver.Repository;

import com.driver.model.Airport;import com.driver.model.City;import com.driver.model.Flight;import com.driver.model.Passenger;import org.springframework.stereotype.Repository;import java.util.ArrayList;import java.util.Date;import java.util.HashMap;import java.util.List;


@Repository
public class AirportRepository
{

    HashMap<String,Airport> airportDb=new HashMap<>();

    HashMap<Integer,Passenger> passengerDb=new HashMap<>();

    HashMap<Integer,Flight> flightDb=new HashMap<>();

    HashMap<Integer,List<Integer>> flightPassengerDb=new HashMap<>();

    public void addAirport(Airport airport)
    {
        String key=airport.getAirportName();
        airportDb.put(key,airport);

    }

    public String getLargestAirportName()
    {
        int max=0;
        for (Airport airport: airportDb.values())
        {
            if(airport.getNoOfTerminals()>max)
                max=airport.getNoOfTerminals();
        }

        String LargestAirportName="";
        for (Airport airport: airportDb.values())
        {
            if(airport.getNoOfTerminals()==max)
            {
                if(LargestAirportName.equals("") || airport.getAirportName().compareTo(LargestAirportName)<0)
                {
                    LargestAirportName=airport.getAirportName();
                }
            }
        }

        return LargestAirportName;

    }

    public void addPassenger(Passenger passenger)
    {
        int key=passenger.getPassengerId();
        passengerDb.put(key,passenger);
    }

    public void addFlight(Flight flight)
    {
        int key=flight.getFlightId();
        flightDb.put(key,flight);
    }

    public String bookATicket(int flightId,int passengerId)
    {

        List<Integer> passengers;
        if(flightPassengerDb.containsKey(flightId))
        {

            passengers=flightPassengerDb.get(flightId);

            if(passengers.size()==flightDb.get(flightId).getMaxCapacity())
                return "FAILURE";
            if(passengers.contains(passengerId))
                return "FAILURE";

        }
        else
        {
            passengers=new ArrayList<>();
            if(passengers.size()==flightDb.get(flightId).getMaxCapacity())
                return "FAILURE";
        }
        passengers.add(passengerId);
        flightPassengerDb.put(flightId,passengers);
        return "SUCCESS";


    }

    public String cancelATicket(int flightId,int passengerId)
    {
        if(!flightPassengerDb.containsKey(flightId))
        {
            return "FAILURE";
        }

        List<Integer> passengers=flightPassengerDb.get(flightId);

        if(!passengers.contains(passengerId))
        {
            return "FAILURE";
        }

        flightPassengerDb.remove(Integer.valueOf(passengerId));

        return "SUCCESS";

    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity,City toCity)
    {

        double minDuration=Double.MAX_VALUE;
        for(Flight flight: flightDb.values())
        {
            if(flight.getFromCity()==fromCity && flight.getToCity()==toCity)
            {
                if(flight.getDuration()<minDuration)
                    minDuration=flight.getDuration();
            }
        }

        if(minDuration==Double.MAX_VALUE)
            return -1.;

        return minDuration;


    }

    public int getNumberOfPeopleOn(Date date,String airportName)
    {
        if(airportDb.containsKey(airportName))
        {

            City currentCity = airportDb.get(airportName).getCity();

            int cnt=0;
            for(Flight flight: flightDb.values())
            {
                if(flight.getToCity()==currentCity || flight.getFromCity()==currentCity)
                {
                    if(flight.getFlightDate().compareTo(date)==0)
                    {
                        cnt+=flightPassengerDb.get(flight.getFlightId()).size();
                    }
                }
            }

            return cnt;
        }

        return 0;
    }

    public int calculateFlightFare(int flightId)
    {
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50

        int price=3000;

        if(flightPassengerDb.containsKey(flightId))
        {
            price+=flightPassengerDb.get(flightId).size()*50;
        }

        return price;
    }

    public int countOfBookingsDoneByPassengerAllCombined(int passengerId)
    {
        int cnt=0;
        for (List<Integer> passengerList:flightPassengerDb.values())
        {
            if(passengerList.contains(passengerId))
                cnt++;

        }

        return cnt;
    }
    public String getAirportNameFromFlightId(int flightId)
    {
        if(flightDb.containsKey(flightId))
        {
            City currentCity=flightDb.get(flightId).getFromCity();

            for(Airport airport: airportDb.values())
            {
                if(airport.getCity()==currentCity)
                    return airport.getAirportName();
            }
        }

        return null;
    }
    public int calculateRevenueOfAFlight(int flightId)
    {
        if(flightPassengerDb.containsKey(flightId))
        {
            int noOfPassenger=flightPassengerDb.get(flightId).size();
            int revenue=0;
            for(int i=1;i<=noOfPassenger;i++)
            {
                revenue=revenue+3000+(i-1)*50;
            }

            return revenue;
        }

        return 0;
    }
}