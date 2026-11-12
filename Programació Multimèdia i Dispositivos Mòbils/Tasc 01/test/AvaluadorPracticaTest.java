/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Javi
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvaluadorPracticaTest {

    private final String javaClasspath =
            System.getProperty("java.class.path");

    @Test
    public void testNivell1_FiltreLogIsolated() throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", javaClasspath, "FiltreLog", "ERROR");

        Process p = pb.start();

        String texto = "2026-09-30 INFO Inici\n"
                + "2026-09-30 ERROR Fallada 1\n"
                + "2026-09-30 ERROR Fallada 2\n";

        try (OutputStream os = p.getOutputStream()) {
            os.write(texto.getBytes(StandardCharsets.UTF_8));
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));

        String resultado = br.readLine();

        assertEquals("2", resultado);
        assertEquals(0, p.waitFor());

        System.out.println("NIVELL 1: CORRECTE");
    }

    @Test
    public void testNivell2_GestioErrors() throws Exception {

        File logErrors = new File("errors_filtre.log");
        logErrors.delete();

        ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", javaClasspath, "FiltreLog", "ERROR");

        pb.redirectError(logErrors);

        Process p = pb.start();

        p.getOutputStream().close();

        int codigo = p.waitFor();

        assertEquals(1, codigo);
        assertTrue(logErrors.exists());

        System.out.println("NIVELL 2: CORRECTE");
    }

    @Test
    public void testNivell3_AnalitzadorPrincipalComplete() throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
                "java", "-cp", javaClasspath, "AnalitzadorPrincipal");

        Process p = pb.start();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));

        String linea;
        String resultado = "";

        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("RESULTAT:")) {
                resultado = linea;
            }
        }

        assertEquals(0, p.waitFor());

        assertTrue(resultado.contains("ERRORS=3"));
        assertTrue(resultado.contains("WARNINGS=3"));
        assertTrue(resultado.contains("EXIT_CODE=0"));

        System.out.println("NIVELL 3: CORRECTE");
    }
}
