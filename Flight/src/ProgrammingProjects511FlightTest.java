import java.time.*;
import java.time.format.*;


/**
 * the runner class for the Flight class. Tests out Flight methods.
 */
public class ProgrammingProjects511FlightTest
{
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a[ z]");

    public static void main(String[] args)
    {
        Flight flight1;
        Flight flight2;
        try 
        {
            flight1 = new Flight("Delta", 2200, "Atlanta", "Los Angeles", ZonedDateTime.parse("Oct 23 2023 at 10:15 AM EDT", formatter), ZonedDateTime.parse("Oct 23 2023 at 01:00 PM PDT", formatter));
            flight2 = new Flight("American Airlines", 3120, "Los Angeles", "Seattle", ZonedDateTime.parse("Oct 23 2023 at 01:15 PM PDT", formatter), ZonedDateTime.parse("Oct 23 2023 at 03:15 PM PDT", formatter));
        } catch (DateTimeParseException e)
        {
            System.out.println("Error: date is parsed incorrectly");
            return;
        }
        System.out.println(flight1.getAirlineName());
        System.out.println(flight2.getOriginCity());
        flight1.setAirlineName("jetBlue");
        System.out.println(flight1.getAirlineName());
        System.out.println("\n");

        System.out.println(flight1 + "\n");
        System.out.println(flight2 + "\n");
        System.out.println(flight1.timeUntilFlight()+ "\n");
        System.out.println(flight1.haveTimeToMakeFlight(flight2));
    }

}
