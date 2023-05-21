package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Transactional
    public void cadastrar(final Veiculo veiculo){

        Assert.isTrue(veiculo.getPlaca() != null, "ERRO, PLACA JÁ CADASTRADO");
        String regexPlacaNova = "^([A-Z]{3})-([0-9]{4})$";
        String regexPlacaAntiga = "^([A-Z]{3})-(d{4})$";
        Assert.isTrue(veiculo.getPlaca().matches(regexPlacaNova) || veiculo.getPlaca().matches(regexPlacaAntiga), "PLACA INVALIDA");

        this.veiculoRepository.save(veiculo);

    }

    @Transactional
    public void editar(final Veiculo veiculo){
        final Veiculo veiculoBanco = this.veiculoRepository.findById(veiculo.getId()).orElse(null);

        Assert.isTrue(veiculoBanco != null || !veiculoBanco.getId().equals(veiculo.getId()), "Registro não identificado!");
        String regexPlaca = "^([A-Z]{3})-([0-9]{4})$";
        Assert.isTrue(veiculo.getPlaca().matches(regexPlaca), "PLACA INVALIDA");


        this.veiculoRepository.save(veiculo);

    }

    @Transactional
    public void delete(final Veiculo veiculo){
        final Veiculo veiculoBanco = this.veiculoRepository.findById(veiculo.getId()).orElse(null);

        if(veiculoBanco == null){
            throw new RuntimeException("Veiculo não encontrado");
        }
        if(!this.veiculoRepository.findByModeloId(veiculoBanco.getId()).isEmpty()){
            veiculoBanco.setAtivo(false);
        }else{
            this.veiculoRepository.delete(veiculoBanco);
        }

    }

}
