package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_veiculo", schema = "public")
public class Veiculo extends AbstractEntity{

    @Getter@Setter
    @Column(name = "placa", nullable = false, unique = true, length = 15)
    private String placa;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;
    @Enumerated(EnumType.STRING)
    @Getter@Setter
    @Column(name = "cor", nullable = false)
    private Cor cor;
    @Enumerated(EnumType.STRING)
    @Getter@Setter
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;
    @Getter@Setter
    @Column(name = "ano", nullable = false)
    private int ano;

}
