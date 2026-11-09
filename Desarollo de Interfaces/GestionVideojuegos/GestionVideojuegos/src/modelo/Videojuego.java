/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Javi
 */

package modelo;

import java.util.HashMap;
import java.util.Map;

public class Videojuego {

    private String titulo;
    private String genero;
    private Map<String, Integer> plataformas;

    public Videojuego(String titulo, String genero) {
        this.titulo = titulo;
        this.genero = genero;
        this.plataformas = new HashMap<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public Map<String, Integer> getPlataformas() {
        return plataformas;
    }

    public void agregarPlataforma(String plataforma, int unidades) {
        plataformas.put(plataforma, unidades);
    }

    public int getUnidadesDisponibles(String plataforma) {
        return plataformas.getOrDefault(plataforma, 0);
    }

    public void modificarStock(String plataforma, int cantidad) {
        plataformas.put(plataforma,
                getUnidadesDisponibles(plataforma) + cantidad);
    }
}
