/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Javi
 */

import java.io.*;
import java.util.concurrent.*;

public class AnalitzadorPrincipal {

    public static void main(String[] args) throws Exception {

        String texto =
                "INFO: Inici del servidor\n"
                + "ERROR: Fallada 1\n"
                + "WARNING: Memoria alta\n"
                + "ERROR: Fallada 2\n"
                + "WARNING: Connexio lenta\n"
                + "ERROR: Acces denegat\n"
                + "WARNING: Disc ple\n";

        File archivoErrores = new File("errors_filtre.log");

        String java = System.getProperty("java.home")
                + File.separator + "bin"
                + File.separator + "java";

        String classpath = System.getProperty("java.class.path");

        ProcessBuilder pbError = new ProcessBuilder(
                java, "-cp", classpath, "FiltreLog", "ERROR");

        ProcessBuilder pbWarning = new ProcessBuilder(
                java, "-cp", classpath, "FiltreLog", "WARNING");

        pbError.redirectError(archivoErrores);
        pbWarning.redirectError(
                ProcessBuilder.Redirect.appendTo(archivoErrores));

        Process procesoError = pbError.start();
        Process procesoWarning = pbWarning.start();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<?> envioError = executor.submit(() -> {
            try (OutputStream out = procesoError.getOutputStream()) {
                out.write(texto.getBytes("UTF-8"));
                out.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Future<?> envioWarning = executor.submit(() -> {
            try (OutputStream out = procesoWarning.getOutputStream()) {
                out.write(texto.getBytes("UTF-8"));
                out.flush();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Future<String> lecturaError = executor.submit(() -> {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(procesoError.getInputStream()))) {
                return br.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Future<String> lecturaWarning = executor.submit(() -> {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(procesoWarning.getInputStream()))) {
                return br.readLine();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        envioError.get();
        envioWarning.get();

        String errores = lecturaError.get();
        String warnings = lecturaWarning.get();

        procesoError.waitFor();
        procesoWarning.waitFor();

        int codigoError = procesoError.exitValue();
        int codigoWarning = procesoWarning.exitValue();

        int codigoFinal = (codigoError == 0 && codigoWarning == 0) ? 0 : 1;

        executor.shutdown();

        System.out.println("RESULTAT: ERRORS=" + errores
                + " | WARNINGS=" + warnings
                + " | EXIT_CODE=" + codigoFinal);
    }
}
