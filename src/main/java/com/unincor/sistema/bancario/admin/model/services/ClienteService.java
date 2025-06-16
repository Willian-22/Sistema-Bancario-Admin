/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroExceptions;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author willian
 */
public class ClienteService {

    private final ClienteDao clienteDao = new ClienteDao();

    public void cadastrarCliente(Cliente cliente) throws CadastroExceptions {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroExceptions("O nome do cliente é obrigatório.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroExceptions("O CPF do cliente é obrigatório.");
        }
        // Verifica se já existe cliente com o mesmo CPF
        Cliente clienteBusca = clienteDao
                .buscarClientePorCpfCliente(cliente.getCpf());
        if (clienteBusca != null) {
            throw new CadastroExceptions("Já existe um cliente cadastrado com este CPF.");
        }
        if (cliente.getDataNascimento() == null) {
            throw new CadastroExceptions("A data de nascimento do cliente é obrigatória.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new CadastroExceptions("O e-mail do cliente é obrigatório.");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new CadastroExceptions("O telefone do cliente é obrigatório.");
        }
        if (cliente.getSenhaHash() == null || cliente.getSenhaHash().isBlank()) {
            throw new CadastroExceptions("A senha do cliente (em hash) é obrigatória.");
        }
        // Insere o cliente no banco de dados
        clienteDao.inserirCliente(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDao.buscarTodosClientes();
    }

    public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();
        LocalDate dataNascimento = LocalDate.of(2003, 04, 26);
        Cliente cliente = new Cliente(null, "Oracle", "123456789", dataNascimento, "univachest@gmail.com", "3599874568", "1234");

        try {
            clienteService.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (CadastroExceptions e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}