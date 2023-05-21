package br.com.uniamerica.estacionamento.service;


import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;


    @Transactional
    public void cadastrar(final Marca marca){

        Assert.isTrue(marca.getNome() != null, "ERRO, MARCA JÁ CADASTRADA");

        this.marcaRepository.save(marca);

    }

    @Transactional
    public void editar(final Marca marca){
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);

        Assert.isTrue(marcaBanco != null || !marcaBanco.getId().equals(marca.getId()), "Registro não identificado!");
        Assert.isTrue(marca.getNome() != null, "MARCA NÃO REGISTRADA");

        this.marcaRepository.save(marca);

    }

    @Transactional
    public void delete(final Marca marca){
        final Marca marcaBanco = this.marcaRepository.findById(marca.getId()).orElse(null);

        if (marcaBanco == null){
            throw new RuntimeException("Condutor não encontrado");
        }else{
            this.marcaRepository.delete(marcaBanco);
        }

    }

}
