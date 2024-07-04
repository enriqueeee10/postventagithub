/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class APIDNI {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Ingrese el número de DNI: ");
        String dni = inputScanner.nextLine();
        
        try {
            // Configurar los parámetros
            String jsonInputString = "{\"dni\": \"" + dni + "\"}";
            
            // URL de la API
            URL url = new URL("https://apiperu.dev/api/dni");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer 1a81bf76be9260cd5655864684887fcd70c30bca8beef58b0b0081b165470bde");
            conn.setDoOutput(true);
            
            // Enviar los parámetros
            try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            
            // Leer la respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (Scanner responseScanner = new Scanner(conn.getInputStream(), "utf-8")) {
                    String responseBody = responseScanner.useDelimiter("\\A").next();
                    System.out.println(responseBody);
                }
            } else {
                System.out.println("HTTP error code : " + responseCode);
            }
            
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        inputScanner.close();
    }
}
