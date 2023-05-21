package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.entity.Veiculo;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.ConfiguracaoRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private CondutorRepository condutorRepository;

    private ConfiguracaoRepository configuracaoRepository;

    @Transactional
    public void cadastrar(final Movimentacao movimentacao){

        Assert.isTrue(movimentacao.getVeiculo() != null, "ERRO, VEICULO NÃO ENCONTRADO");
        Assert.isTrue(movimentacao.getCondutor() != null, "ERRO, CONDUTOR NÃO ENCONTRADO");
        Assert.isTrue(movimentacao.getEntrada() != null, "ERRO, ENTRADA NÃO ENCONTRADA");

        this.movimentacaoRepository.save(movimentacao);

    }

    @Transactional
    public void editar(final Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(movimentacao.getId()).orElse(null);

        Assert.isTrue(movimentacao.getVeiculo() != null, "ERRO, VEICULO NÃO ENCONTRADO");
        Assert.isTrue(movimentacao.getCondutor() != null, "ERRO, CONDUTOR NÃO ENCONTRADO");
        Assert.isTrue(movimentacao.getEntrada() != null, "ERRO, ENTRADA NÃO ENCONTRADA");

        final Long id_veiculo = movimentacao.getVeiculo().getId();
        final Veiculo veiculo = this.veiculoRepository.findById(id_veiculo).orElse(null);
        Assert.isTrue(veiculo != null, "ERRO, VEICULO INEXISTENTE");

        final Long id_condutor = movimentacao.getCondutor().getId();
        final Condutor condutor = this.condutorRepository.findById(id_condutor).orElse(null);
        Assert.isTrue(condutor != null, "ERRO, CONDUTOR INEXISTENTE");

        this.movimentacaoRepository.save(movimentacao);

    }

    @Transactional
    public void delete(final Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(movimentacao.getId()).orElse(null);

        if(movimentacaoBanco == null){
            throw new RuntimeException("Movimentação não encontrado");
        }
        if(!this.movimentacaoRepository.findByVeiculoId(movimentacaoBanco.getId()).isEmpty() || !this.movimentacaoRepository.findByCondutorId(movimentacaoBanco.getId()).isEmpty()){
            movimentacaoBanco.setAtivo(false);
        }else{
            this.movimentacaoRepository.delete(movimentacaoBanco);
        }

    }

    public void sair(final Long id){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);




    }

}
