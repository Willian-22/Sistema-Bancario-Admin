package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroExceptions;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author willian
 */
public class GerenteService {

    private final GerenteDao gerenteDao = new GerenteDao();

    public void cadastrarGerente(Gerente gerente) throws CadastroExceptions {
        if (gerente.getNome() == null || gerente.getNome().isBlank()) {
            throw new CadastroExceptions("O nome do gerente é obrigatório.");
        }
        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroExceptions("O CPF do gerente é obrigatório.");
        }
        // Verifica se já existe gerente com o mesmo CPF
        Gerente gerenteBusca = gerenteDao.buscarGerentePorCpfGerente(gerente.getCpf());
if (gerenteBusca != null) {
    throw new CadastroExceptions("Já existe um gerente cadastrado com este CPF.");
}
        if (gerente.getDataNascimento() == null) {
            throw new CadastroExceptions("A data de nascimento do gerente é obrigatória.");
        }
        if (gerente.getEmail() == null || gerente.getEmail().isBlank()) {
            throw new CadastroExceptions("O e-mail do gerente é obrigatório.");
        }
        if (gerente.getTelefone() == null || gerente.getTelefone().isBlank()) {
            throw new CadastroExceptions("O telefone do gerente é obrigatório.");
        }
        if (gerente.getSenhaHash() == null || gerente.getSenhaHash().isBlank()) {
            throw new CadastroExceptions("A senha do gerente (em hash) é obrigatória.");
        }
        if (gerente.getAgencia() == null) {
            throw new CadastroExceptions("O codigo da agência é obrigatório.");
        }

        gerenteDao.inserirGerente(gerente);
    }

    public List<Gerente> listarGerentes() {
        return gerenteDao.buscarTodosGerentes();
    }
    

    public static void main(String[] args) {
    GerenteService gerenteService = new GerenteService();
        AgenciaDao agenciaDao = new AgenciaDao();

    // Suponha que você quer o gerente na agência de ID 1
    Agencia agencia = agenciaDao.buscarAgenciaPorId(1L);

    if (agencia == null) {
        System.out.println("Agência não encontrada no banco.");
        return;
    }

    Gerente gerente = new Gerente();
    gerente.setNome("João Silva");
    gerente.setCpf("12345678901");
    gerente.setDataNascimento(LocalDate.of(1990, 5, 10));
    gerente.setEmail("joaos.silva@email.com");
    gerente.setTelefone("35991234567");
    gerente.setSenhaHash("senha123");
    gerente.setAgencia(agencia);  

    try {
        gerenteService.cadastrarGerente(gerente);
        System.out.println("Gerente cadastrado com sucesso!");
    } catch (CadastroExceptions e) {
        System.out.println("Erro ao cadastrar gerente: " + e.getMessage());
    }
}

}
