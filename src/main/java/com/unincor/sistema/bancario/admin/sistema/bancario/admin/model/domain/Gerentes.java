/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.sistema.bancario.admin.model.domain;

import java.time.LocalDate;

/**
 *
 * @author paran
 */
public class Gerentes extends Pessoa {

    private Long idGerente;
    private Agencia agencia;

    public Gerentes(Long idGerente) {
        this.idGerente = idGerente;
        
    }

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public Agencia getSgencia() {
        return agencia;
    }

    public void setSgencia(Agencia sgencia) {
        this.agencia = sgencia;
    }

    
    

    

    
    
}
