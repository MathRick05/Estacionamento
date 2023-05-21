package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Condutor;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/estacionamento/condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id){
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);

        return condutor == null ? ResponseEntity.badRequest().body("Nenhum condutor identificado") : ResponseEntity.ok(condutor);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(this.condutorRepository.findAll());
    }
    @GetMapping("/lista/ativos")
    public ResponseEntity<?> findAllAtivos(){
        return ResponseEntity.ok(this.condutorRepository.findByAtivo());
    }
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Condutor condutor){
        try{
            this.condutorService.cadastrar(condutor);
            return ResponseEntity.ok("Salvo com Sucesso");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> editar (
            @RequestParam("id") final Long id,
            @RequestBody Condutor condutor
    ){
        try {
            final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

            this.condutorService.editar(condutor);
            return ResponseEntity.ok("Atualizado com sucesso");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ){
        try{
            final Condutor condutorBanco = this.condutorRepository.findById(id).orElse(null);

            this.condutorService.delete(condutorBanco);
            return ResponseEntity.ok("Marca desativada");
        }catch (Exception erro){
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }
}
