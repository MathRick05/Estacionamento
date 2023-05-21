package br.com.uniamerica.estacionamento.repository;

import br.com.uniamerica.estacionamento.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CondutorRepository extends JpaRepository<Condutor,Long> {

    @Query("from Condutor where ativo = true")
    public List<Condutor> findByAtivo();

}
