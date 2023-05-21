package br.com.uniamerica.estacionamento.service;
import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.awt.print.PrinterException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public void cadastrar(final Condutor condutor){

        Assert.hasText(condutor.getNome(), "Campo Obrigatório");
        Assert.isTrue(condutor.getNome() != null, "ERRO, CONDUTOR JÁ CADASTRADO");
        Assert.isTrue(condutor.getNome().length() < 100,"NUMERO DE CARACTERES MAIOR QUE O PERMITIDO");
        Assert.hasText(condutor.getCpf(), "Campo Obrigatório");
        Assert.isTrue(condutor.getCpf() != null, "ERRO, CPF JÁ CADASTRADO");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(condutor.getCpf().matches(regexCpf), "ERRO, CPF INVALIDO OU FORA DO FORMATO PADRÃO");
        Assert.hasText(condutor.getTelefone(), "Campo Obrigatório");
        Assert.isTrue(condutor.getTelefone() != null, "ERRO, TELEFONE JÁ CADASTRADO");
        String regexTel = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Assert.isTrue(condutor.getTelefone().matches(regexTel), "ERRO, TELEFONE INVALIDO OU FORA DO FORMATO PADRÃO");

        this.condutorRepository.save(condutor);

    }

    @Transactional
    public void editar(final Condutor condutor){
        final Condutor condutorBanco = this.condutorRepository.findById(condutor.getId()).orElse(null);

        Assert.isTrue(condutorBanco != null || !condutorBanco.getId().equals(condutor.getId()), "Registro não identificado!");
        Assert.hasText(condutor.getNome(), "Campo Obrigatório");
        Assert.hasText(condutor.getCpf(), "Campo Obrigatório");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Assert.isTrue(condutor.getCpf().matches(regexCpf), "ERRO, CPF INVALIDO OU FORA DO FORMATO PADRÃO");
        Assert.hasText(condutor.getTelefone(), "Campo Obrigatório");
        String regexTel = "^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$";
        Assert.isTrue(condutor.getTelefone().matches(regexTel), "ERRO, TELEFONE INVALIDO OU FORA DO FORMATO PADRÃO");

        this.condutorRepository.save(condutor);

    }

    @Transactional
    public void delete(final Condutor condutor){
        final Condutor condutorBanco = this.condutorRepository.findById(condutor.getId()).orElse(null);

        if(condutorBanco == null){
            throw new RuntimeException("Condutor não encontrado");
        }
        if(!this.movimentacaoRepository.findByCondutorId(condutorBanco.getId()).isEmpty()){
            condutorBanco.setAtivo(false);
        }else{
            this.condutorRepository.delete(condutorBanco);
        }

    }

}
