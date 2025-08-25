package demo5;

import java.net.*;
import java.io.*;
import java.util.*;

public class CurrencyConverter {

    private static final String API_URL = "https://open.er-api.com/v6/latest/"; 
    // This API is free and does not need an API key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Currency selection
        System.out.print("Enter base currency code (e.g., USD): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency code (e.g., INR): ");
        String target = scanner.next().toUpperCase();

        // Amount input
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double rate = fetchExchangeRate(base, target);
            double converted = amount * rate;
            System.out.printf("Converted Amount: %.4f %s%n", converted, target);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    private static double fetchExchangeRate(String base, String target) throws IOException {
        String urlStr = API_URL + base;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonText = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonText.append(line);
        }
        reader.close();

        // Example response contains "INR":83.25, "EUR":0.91 etc.
        String json = jsonText.toString();
        String search = "\"" + target + "\":";
        int index = json.indexOf(search);
        if (index == -1) {
            throw new IOException("Currency not found: " + target);
        }
        int start = index + search.length();
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);

        String value = json.substring(start, end).trim();
        return Double.parseDouble(value);
    }
}
