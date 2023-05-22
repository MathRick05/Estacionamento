package br.com.uniamerica.estacionamento.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_condutor", schema = "public")
public class Condutor extends AbstractEntity{

    @Getter@Setter
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Getter@Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;
    @Getter@Setter
    @Column(name = "telefone", nullable = false, unique = true, length = 20)
    private String telefone;
    @Getter@Setter
    @Column(name = "timePago")
    private BigDecimal tempoPago;
    @Getter@Setter
    @Column(name = "timeDesconto")
    private BigDecimal tempoDesconto;

}
