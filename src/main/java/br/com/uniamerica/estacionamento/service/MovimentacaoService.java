package br.com.uniamerica.estacionamento.service;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.entity.Configuracao;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

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
    public void editar(final Long id, final Movimentacao movimentacao){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

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
    public void delete(final Long id){
        final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

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
        Assert.isTrue(movimentacaoBanco != null, "Não foi possivel identificar o registro informado");
        System.out.println(movimentacaoBanco + "foi");

        final LocalDateTime saida = LocalDateTime.now();
        Duration duracao = Duration.between(movimentacaoBanco.getEntrada(), saida);
        System.out.println(duracao + "foi");

        final Configuracao config = this.configuracaoRepository.findById(1L).orElse(null);
        Assert.isTrue(config != null, "Configuracoes não registrada");
        System.out.println(config + "foi");

        final Condutor condutor = this.condutorRepository.findById(movimentacaoBanco.getCondutor().getId()).orElse(null);
        Assert.isTrue(condutor != null, "Condutor não registrado");
        System.out.println(condutor + "foi");

        movimentacaoBanco.setSaida(saida);
        System.out.println(saida + "foi");
        movimentacaoBanco.setTempoHoras(duracao.toHoursPart());
        movimentacaoBanco.setTempoMinutos(duracao.toMinutesPart());

        final BigDecimal horas = BigDecimal.valueOf(duracao.toHoursPart());
        final BigDecimal min = BigDecimal.valueOf(duracao.toMinutesPart()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_EVEN);
        BigDecimal preco = config.getValorHora().multiply(horas).add(config.getValorHora().multiply(min));

        final BigDecimal tempoPago = condutor.getTempoPago() != null ? condutor.getTempoPago() : new BigDecimal(0);

        BigDecimal desconto;

        if (tempoPago.compareTo(new BigDecimal(config.getTempoParaDesconto())) >= 0){



        }

}

}