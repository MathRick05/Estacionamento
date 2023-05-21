package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_modelo", schema = "public")
public class Modelo extends AbstractEntity{

    @Getter@Setter
    @Column(name = "modelo", nullable = false, length = 30)
    private String nome;
    @Getter@Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

}
