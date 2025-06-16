/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroExceptions;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.util.List;

/**
 *
 * @author willian
 */
public class FuncionarioService {
     private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public void cadastrarFuncionario(Funcionario funcionario) throws CadastroExceptions {
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new CadastroExceptions("O nome do funcionário é obrigatório.");
        }
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroExceptions("O CPF do funcionário é obrigatório.");
        }

        // Verificar se já existe funcionário com o mesmo CPF
        Funcionario funcionarioBusca = funcionarioDao
                .buscarFuncionarioPorCpfFuncionario(funcionario.getCpf());
        if (funcionarioBusca != null) {
            throw new CadastroExceptions("Já existe um funcionário cadastrado com este CPF.");
        }

        if (funcionario.getDataNascimento() == null) {
            throw new CadastroExceptions("A data de nascimento do funcionário é obrigatória.");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank()) {
            throw new CadastroExceptions("O e-mail do funcionário é obrigatório.");
        }
        if (funcionario.getTelefone() == null || funcionario.getTelefone().isBlank()) {
            throw new CadastroExceptions("O telefone do funcionário é obrigatório.");
        }
        if (funcionario.getSenhaHash() == null || funcionario.getSenhaHash().isBlank()) {
            throw new CadastroExceptions("A senha do funcionário (em hash) é obrigatória.");
        }
        if (funcionario.getTurno() == null || funcionario.getTurno().isBlank()) {
            throw new CadastroExceptions("O turno do funcionário é obrigatório.");
        }

        // Inserir no banco de dados
        funcionarioDao.inserirFuncionario(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDao.buscarTodosFuncionarios();
    }

    public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João Silva");
        funcionario.setCpf("12345678900");
        funcionario.setDataNascimento(java.time.LocalDate.of(1992, 8, 15));
        funcionario.setEmail("joao.silva@email.com");
        funcionario.setTelefone("35991234567");
        funcionario.setSenhaHash("senha123");
        funcionario.setTurno("MANHA");

        try {
            funcionarioService.cadastrarFuncionario(funcionario);
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (CadastroExceptions e) {
            System.out.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }
    
}