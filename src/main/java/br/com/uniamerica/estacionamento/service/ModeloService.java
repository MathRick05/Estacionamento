package br.com.uniamerica.estacionamento.service;


import br.com.uniamerica.estacionamento.entity.Modelo;
import br.com.uniamerica.estacionamento.repository.MarcaRepository;
import br.com.uniamerica.estacionamento.repository.ModeloRepository;
import br.com.uniamerica.estacionamento.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private MarcaRepository marcaRepository;


    @Transactional
    public void cadastrar(final Modelo modelo){

        Assert.isTrue(modelo.getNome() != null, "ERRO, MODELO JÁ CADASTRADO");

        this.modeloRepository.save(modelo);

    }

    @Transactional
    public void editar(final Modelo modelo){
        final Modelo modeloBanco = this.modeloRepository.findById(modelo.getId()).orElse(null);

        Assert.isTrue(modeloBanco != null || !modeloBanco.getId().equals(modelo.getId()), "Registro não identificado!");
        Assert.isTrue(modelo.getNome() != null, "MODELO NÃO REGISTRADA");

        this.modeloRepository.save(modelo);

    }

    @Transactional
    public void delete(final Modelo modelo){
        final Modelo modeloBanco = this.modeloRepository.findById(modelo.getId()).orElse(null);

        if(modeloBanco == null){
            throw new RuntimeException("Modelo não encontrado");
        }
        if(!this.modeloRepository.findByMarcaId(modeloBanco.getId()).isEmpty()){
            modeloBanco.setAtivo(false);
        }else{
            this.modeloRepository.delete(modeloBanco);
        }

    }

}
