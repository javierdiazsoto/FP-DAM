package cuentas;

/**
 * Clase principal que contiene el método main y la lógica de operación de la cuenta.
 */
public class Main {

    /**
     * Método principal que inicia la ejecución del programa.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */

    public static void main(String[] args) {
        CCuenta cuenta1;
        float cantidad = 2500;

        cuenta1 = new CCuenta("Antonio López", "1000-2365-85-1230456789", 2500, 0);
        operativa_cuenta(cuenta1, cantidad);
    }

    /**
     * Realiza operaciones de retirada e ingreso en la cuenta especificada.
     * @param cuenta1 La cuenta en la que se realizarán las operaciones.
     * @param cantidad La cantidad inicial (no se utiliza en las operaciones).
     */

    private static void operativa_cuenta(CCuenta cuenta1, float cantidad) {
        double saldoActual = cuenta1.estado();
        System.out.println("El saldo actual es " + saldoActual);


        try {
            System.out.println("Ingreso en cuenta");
            cuenta1.ingresar(695);
        } catch (Exception e) {
            System.out.println("Fallo al ingresar: " + e.getMessage());
        }
    }
}