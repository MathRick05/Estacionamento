package br.com.uniamerica.estacionamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Getter@Setter
    @Column(name = "dt_cadastro")
    private LocalDateTime cadastro;
    @Getter@Setter
    @Column(name = "dt_edicao")
    private LocalDateTime edicao;
    @Getter@Setter
    @Column(name = "ativo")
    private boolean ativo;

    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }

    @PreUpdate
    private void preUpdate(){
        this.edicao = LocalDateTime.now();
    }

}
