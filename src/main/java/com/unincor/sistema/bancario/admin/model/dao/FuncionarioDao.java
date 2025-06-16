package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionarioDao {

    public void inserirFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash, turno) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setDate(3, Date.valueOf(funcionario.getDataNascimento()));
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getTelefone());
            ps.setString(6, funcionario.getSenhaHash());
            ps.setString(7, funcionario.getTurno());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, "Erro ao inserir funcionário", ex);
        }
    }

    public List<Funcionario> buscarTodosFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var funcionarioTemp = construirFuncionarioSql(rs);
                funcionarios.add(funcionarioTemp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, "Erro ao buscar todos os funcionários", ex);
        }
        return funcionarios;
    }

    public Funcionario buscarFuncionarioPorId(Long idFuncionario) {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idFuncionario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirFuncionarioSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, "Erro ao buscar funcionário por ID", ex);
        }
        return null;
    }

    public Funcionario construirFuncionarioSql(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(rs.getLong("id_funcionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        funcionario.setEmail(rs.getString("email"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setSenhaHash(rs.getString("senha_hash"));
        funcionario.setTurno(rs.getString("turno"));
        return funcionario;
    }
    
    public Funcionario buscarFuncionarioPorCpfFuncionario(String Cpf) {
        String sql = "SELECT * FROM Funcionarios where cpf = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirFuncionarioSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        var funcionarios = funcionarioDao.buscarTodosFuncionarios();
        funcionarios.forEach(f -> System.out.println("Id: " + f.getIdFuncionario()
                + " Nome: " + f.getNome()));

        var f = funcionarioDao.buscarFuncionarioPorId(1L);
        if (f != null) {
            System.out.println("Id: " + f.getIdFuncionario()
                    + " Nome: " + f.getNome());
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}
