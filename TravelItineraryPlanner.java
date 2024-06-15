package code_alpha;



import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;

public class TravelItineraryPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Travel Itinerary Planner!");
        System.out.print("Enter number of destinations: ");
        int numDestinations = scanner.nextInt();
        scanner.nextLine();

        String[] destinations = new String[numDestinations];
        String[] dates = new String[numDestinations];

        for (int i = 0; i < numDestinations; i++) {
            System.out.print("Enter destination " + (i + 1) + ": ");
            destinations[i] = scanner.nextLine();
            System.out.print("Enter date for destination " + (i + 1) + ": ");
            dates[i] = scanner.nextLine();
        }

        generateTravelPlan(destinations, dates);

        scanner.close();
    }

    private static void generateTravelPlan(String[] destinations, String[] dates) {
        System.out.println("Generating travel plan...");

        integrateMaps(destinations);
        fetchWeatherInformation(destinations, dates);

        for (int i = 0; i < destinations.length; i++) {
            System.out.println("Destination: " + destinations[i] + ", Date: " + dates[i]);
        }

        calculateBudget(destinations);
    }

    private static void integrateMaps(String[] destinations) {
        System.out.println("Integrating maps...");

        // Generate a Google Maps URL to display the destinations
        StringBuilder mapUrl = new StringBuilder("https://www.google.com/maps/dir/");
        for (String destination : destinations) {
            // Encode the destination to ensure special characters are properly handled
            String encodedDestination = URLEncoder.encode(destination, java.nio.charset.StandardCharsets.UTF_8);
            mapUrl.append(encodedDestination).append("/");
        }
        // Remove the trailing slash
        mapUrl.deleteCharAt(mapUrl.length() - 1);

        // Print the URL for the user to access the map
        System.out.println("You can view the route on Google Maps: " + mapUrl.toString());
    }
    
    
    private static void fetchWeatherInformation(String[] destinations, String[] dates) {
        System.out.println("Fetching weather information...");
        // Integrate with weather service
        WeatherService weatherService = new WeatherService();
        Map<String, String> weatherInfo = weatherService.getWeatherInfo(destinations, dates);
        for (String destination : destinations) {
            System.out.println("Weather for " + destination + ": " + weatherInfo.get(destination));
        }
    }


    private static void calculateBudget(String[] destinations) {
        System.out.println("Calculating budget...");

        // Assuming a fixed budget per destination
        double totalBudget = destinations.length * 1000; // $1000 per destination

        System.out.println("Total budget for the trip: $" + totalBudget);
    }
}

