/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.sistema.bancario.admin.model.domain;

/**
 *
 * @author paran
 */
public class Agencia {
    private Long idAgencia;
    private String codigoAgencia;
    private String ciadede;
    private String uf;
     private String numero;
      private String cep;

    public Agencia() {
    }

    public Agencia(Long idAgencia, String codigoAgencia, String ciadede, String uf, String numero, String cep) {
        this.idAgencia = idAgencia;
        this.codigoAgencia = codigoAgencia;
        this.ciadede = ciadede;
        this.uf = uf;
        this.numero = numero;
        this.cep = cep;
    }
      
      
}
