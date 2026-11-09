/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Javi
 */

package modelo;

import java.time.LocalDate;

public class Alquiler {

    private int numeroRegistro;
    private Usuario usuario;
    private Videojuego videojuego;
    private String plataforma;
    private LocalDate fechaInicio;
    private LocalDate fechaDevolucion;
    private boolean devuelto;

    public Alquiler(int numeroRegistro, Usuario usuario,
            Videojuego videojuego, String plataforma) {

        this.numeroRegistro = numeroRegistro;
        this.usuario = usuario;
        this.videojuego = videojuego;
        this.plataforma = plataforma;
        this.fechaInicio = LocalDate.now();
        this.fechaDevolucion = fechaInicio.plusDays(7);
        this.devuelto = false;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Videojuego getVideojuego() {
        return videojuego;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void devolver() {
        devuelto = true;
    }
}
