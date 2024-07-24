package org.example.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    // URL de la API para obtener las tasas de cambio
    private final String apiUrl;
    // Objeto para almacenar las tasas de cambio
    private JsonObject rates;

    // Constructor que inicializa el convertidor con la URL de la API
    public Converter(String apiUrl) {
        this.apiUrl = apiUrl;
        updateRates();  // Actualiza las tasas de cambio al crear el objeto
    }

    // Método para actualizar las tasas de cambio desde la API
    private void updateRates() {
        try {
            // Crea un cliente HTTP para hacer la solicitud
            HttpClient client = HttpClient.newHttpClient();
            // Construye la solicitud HTTP GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            // Envía la solicitud y recibe la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            // Parsea la respuesta JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            // Extrae el objeto "rates" del JSON
            this.rates = jsonObject.getAsJsonObject("rates");
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las tasas de cambio", e);
        }
    }

    // Método para obtener la lista de monedas disponibles
    public List<String> getAvailableCurrencies() {
        // Devuelve una lista de las claves (códigos de moneda) del objeto rates
        return new ArrayList<>(rates.keySet());
    }

    // Método para convertir una cantidad de una moneda a  otra
    public double convert(float amount, String fromCurrency, String toCurrency) {
        // Verifica si las monedas existen en las tasas disponibles
        if (!rates.has(fromCurrency) || !rates.has(toCurrency)) {
            throw new IllegalArgumentException("Moneda no soportada");
        }

        // Obtiene las tasas de cambio para las monedas de origen y destino
        double fromRate = rates.get(fromCurrency).getAsDouble();
        double toRate = rates.get(toCurrency).getAsDouble();

        // Realiza la conversión
        return amount * (toRate / fromRate);
    }
}