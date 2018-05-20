package com.ntu.MyLab.DAO;

import com.ntu.MyLab.Entity.Flight;

import java.util.List;

public interface FlightDAO {
    Flight getFlightById(long id);
    List<Flight> getAllFlight();

    boolean insertFlight(Flight flight);
    boolean updateFlight(Flight flight);
    boolean deleteFlight(long id);
}
