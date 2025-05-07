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
public class Cliente extends Pessoa {

    private Long idCliente;

    public Cliente(Long id, String nome, String cpf, LocalDate dataNascimento, String telefone, String email, String senhaHash) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.senhaHash = senhaHash;
    }

    public Long getId() {
        return idCliente;
    }

    public void setId(Long id) {
        this.idCliente = idCliente;
    }

}
