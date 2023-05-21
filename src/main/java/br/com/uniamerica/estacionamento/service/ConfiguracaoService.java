package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Configuracao;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configRepository;


    @Transactional
    public void cadastrar(final Configuracao config){

        this.configRepository.save(config);
    }

    @Transactional
    public void editar(final Configuracao config){
        final Configuracao configBanco = this.configRepository.findById(config.getId()).orElse(null);

        Assert.isTrue(configBanco != null || !configBanco.getId().equals(config.getId()), "Registro não identificado!");
        Assert.isTrue(config.getInicioExpediente() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.getFimExpediente() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.getValorHora() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.getValorMinutoMulta() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.isGerarDesconto() == false, "DESCONTO DESATIVADO");
        Assert.isTrue(config.getTempoDeDesconto() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.getTempoParaDesconto() == null, "REGISTRO INEXISTENTE");
        Assert.isTrue(config.getVagasCarro() == 0, "VAGAS ZERADAS");
        Assert.isTrue(config.getVagasMoto() == 0, "VAGAS ZERADAS");
        Assert.isTrue(config.getVagasVan() == 0, "VAGAS ZERADAS");

        this.configRepository.save(config);

    }

}
