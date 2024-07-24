package org.example;

import org.example.models.Converter;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final static String API_KEY = "Escibe tu api acá";
    private final static String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Converter converter = new Converter(API_URL);

        List<String> availableCurrencies = converter.getAvailableCurrencies();
        System.out.println("Monedas disponibles y sus cambios actuales");
        for (String currency : availableCurrencies){
            System.out.println(currency);
        }

        System.out.println("\nIngrese la cantidad de dinero: ");
        float amount = input.nextFloat();
        input.nextLine();

        System.out.println("\nIngrese la moneda de origen: ");
        String fromCurrency = input.nextLine().toUpperCase();

        System.out.println("\nIngrese la moneda de destino: ");
        String toCurrency = input.nextLine().toUpperCase();

        try{
            double result = converter.convert(amount, fromCurrency, toCurrency);
            System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);
        } catch(IllegalArgumentException e){
            System.out.println("Error "+e.getMessage());
        }

    }
}