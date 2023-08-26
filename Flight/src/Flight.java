import java.time.format.*;
import java.time.*;

import static java.time.temporal.ChronoUnit.*;

/**
 * This class generates flight objects that represent a one-way flight from one airport to another.
 * These flights are formatted ZonedDateTime objects.
 * This class contains methods that print the time from now until a flight's departure,
 * and prints if there is enough time to make a layover from one flight to another.
 */
public class Flight
{
    /**
     * The airline's name.
     */
    private String airlineName;
    /**
     * The flight number.
     */
    private int flightNumber;
    /**
     * The city the flight departs from.
     */
    private String originCity;
    /**
     * The city the flight is going to.
     */
    private String destinationCity;
    /**
     * The time the flight leaves.
     */
    private ZonedDateTime departureTime;
    /**
     * The time the flight arrives.
     */
    private ZonedDateTime arrivalTime;
    /**
     * The formatter for my time objects. Formats the dates such as: "Jan 01 2023 at 11:59 AM EST"
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a [z]");

    /**
     * Constructs a Flight object.
     * @param n Value of airlineName
     * @param num Value of flightNumber
     * @param o Value of originCity
     * @param d Value of destinationCity
     * @param dep Value of departureTime
     * @param arr Value of arrivalTime
     */
    public Flight(String n, int num, String o, String d, ZonedDateTime dep, ZonedDateTime arr)
    {
        if (MINUTES.between(dep, arr) <= 0)
        {
           return;
        }

        else
        {
            airlineName = n;
            flightNumber = num;
            originCity = o;
            destinationCity = d;
            departureTime = dep;
            arrivalTime = arr;
        }
    }

    /**
     * getter method for originCity
     * @return this Flight's originCity
     */
    public String getOriginCity()
    {
        return originCity;
    }

    /**
     * setter method for originCity
     */
    public void setOriginCity(String originCity)
    {
        this.originCity = originCity;
    }

    /**
     * getter method for airlineName
     * @return this Flight's airlineName
     */
    public String getAirlineName()
    {
        return airlineName;
    }

    /**
     * setter method for airlineName
     */
    public void setAirlineName(String airlineName)
    {
        this.airlineName = airlineName;
    }

    /**
     * getter method for flightNumber
     * @return this Flight's flightNumber
     */
    public int getFlightNumber()
    {
        return flightNumber;
    }

    /**
     * setter method for flightNumber
     */
    public void setFlightNumber(int flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    /**
     * getter method for destinationCity
     * @return this Flight's destinationCity
     */
    public String getDestinationCity()
    {
        return destinationCity;
    }

    /**
     * setter method for destinationCity
     */
    public void setDestinationCity(String destinationCity)
    {
        this.destinationCity = destinationCity;
    }

    /**
     * Method that details how many days and hours from the computer's local time until this Flight object's departure time
     * @return a String detailing the time from now until this object's departure time
     */
    public String timeUntilFlight()
    {
        int amountDays = 0;
        int amountHours = 0;
        try
        {
            amountDays = (int) DAYS.between(ZonedDateTime.now(), departureTime);
            amountHours = (int) HOURS.between(ZonedDateTime.now(), departureTime) % 24;
        }
        catch (NullPointerException e)
        {
            return "Error: impossible flight";
        }

        switch (amountDays)
        {
            case 1:
                return "You have " + amountDays + " day and " + amountHours + " hours before your flight";


            default:
                return "You have " + amountDays + " days and " + amountHours + " hours before your flight";

        }
    }

    /**
     * A method that details whether or not there is enough time to make the connecting flight. Connecting flights can be made if they are at least one hour after the arrival of the first flight AND they are in the same city.
     * @param flight2 the connecting flight
     * @return a String detailing if there is enough time to make the connecting flight or if there is an error
     */
    public String haveTimeToMakeFlight(Flight flight2)
    {
        boolean sameAirport = false;
        boolean canMakeFlight = false;
        try
        {
            sameAirport = this.destinationCity.equals(flight2.originCity);
            canMakeFlight = (int) HOURS.between(this.arrivalTime, flight2.departureTime) > 1;
        }
        catch (NullPointerException e)
        {
            return "Error: impossible flight";
        }

        if (canMakeFlight && sameAirport)
        {
            return "You have enough time to make your next flight.";
        }

        else if (sameAirport)
        {
            return  "You do not have enough time to make your next flight.";
        }

        return "These two flights are not in the same airport.";
    }

    /**
     * returns the Flight object as an organized String
     * @return the organized String
     */
    public String toString()
    {
        try
        {
            return airlineName + " Flight " + flightNumber + ", service from " + originCity + " to " +
                    destinationCity + ". Departs " + departureTime.format(formatter) + ", arrives " + arrivalTime.format(formatter);
        }
        catch (NullPointerException e)
        {
            return "Error: impossible flight";
        }

    }

}