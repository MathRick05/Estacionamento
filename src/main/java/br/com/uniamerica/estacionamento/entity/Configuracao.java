package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "tb_configuracao", schema = "public")
public class Configuracao extends AbstractEntity{

    @Getter@Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
    @Getter@Setter
    @Column(name = "valor_minuto_hora")
    private BigDecimal valorMinutoMulta;
    @Getter@Setter
    @Column(name = "inicio_expediente")
    private LocalTime inicioExpediente;
    @Getter@Setter
    @Column(name = "fim_Expediente")
    private LocalTime fimExpediente;
    @Getter@Setter
    @Column(name = "tempo_para_desconto")
    private BigDecimal tempoParaDesconto;
    @Getter@Setter
    @Column(name = "tempo_de_desconto")
    private BigDecimal tempoDeDesconto;
    @Getter@Setter
    @Column(name = "gerar_desconto")
    private boolean gerarDesconto;
    @Getter@Setter
    @Column(name = "vagas_moto")
    private int vagasMoto;
    @Getter@Setter
    @Column(name = "vagas_carro")
    private int vagasCarro;
    @Getter@Setter
    @Column(name = "vagas_van")
    private int vagasVan;

}
