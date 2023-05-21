package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tb_movimentacao", schema = "public")
public class Movimentacao extends AbstractEntity{

    @Getter@Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "veiculo", nullable = false)
    private Veiculo veiculo;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "motorista", nullable = false)
    private Condutor condutor;
    @Getter@Setter
    @Column(name = "entrada", nullable = false)
    private LocalDateTime entrada;
    @Getter@Setter
    @Column(name = "saida")
    private LocalDateTime saida;
    @Getter@Setter
    @Column(name = "tempo")
    private LocalTime tempo;
    @Getter@Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;
    @Getter@Setter
    @Column(name = "tempo_multa")
    private LocalTime tempoMulta;
    @Getter@Setter
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;
    @Getter@Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Getter@Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Getter@Setter
    @Column(name = "valor_hota")
    private BigDecimal valorHora;
    @Getter@Setter
    @Column(name = "valor_hora_multa")
    private BigDecimal valorHoraMulta;

}