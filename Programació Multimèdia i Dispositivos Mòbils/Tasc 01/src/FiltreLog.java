/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Javi
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FiltreLog {

    public static void main(String[] args) {

        String palabra = "ERROR";

        if (args.length > 0) {
            palabra = args[0];
        }

        int contador = 0;
        boolean hayTexto = false;

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));

            String linea;

            while ((linea = br.readLine()) != null) {

                if (!linea.trim().isEmpty()) {
                    hayTexto = true;
                }

                if (linea.contains(palabra)) {
                    contador++;
                }
            }

            if (!hayTexto) {
                System.err.println("Error: Text buit");
                System.exit(1);
            }

            System.out.println(contador);
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
