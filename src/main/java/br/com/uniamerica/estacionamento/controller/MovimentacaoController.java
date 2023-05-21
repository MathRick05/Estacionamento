package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.entity.Movimentacao;
import br.com.uniamerica.estacionamento.repository.CondutorRepository;
import br.com.uniamerica.estacionamento.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamento.service.ModeloService;
import br.com.uniamerica.estacionamento.service.MovimentacaoService;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estacionamento/movimentacao")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam("id") final Long id) {
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);

        return movimentacao == null ? ResponseEntity.badRequest().body("Nenhuma movimentação encontrada") :
                ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }

    @GetMapping("/lista/abertas")
    public ResponseEntity<?> findAllAtivos() {
        return ResponseEntity.ok(this.movimentacaoRepository.findByAtivos());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Movimentacao movimentacao) {
        try {
            this.movimentacaoService.cadastrar(movimentacao);
            return ResponseEntity.ok("Salvo com Sucesso");
        } catch (Exception erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(
            @RequestParam("id") final Long id,
            @RequestBody Movimentacao movimentacao
    ) {
        try {
            final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

            this.movimentacaoRepository.save(movimentacao);
            return ResponseEntity.ok("Movimentacao atualizado");
        } catch (Exception erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ) {
        try {
            final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

            this.movimentacaoService.delete(movimentacaoBanco);
            return ResponseEntity.ok("Movimentação desativada");
        } catch (Exception erro) {
            return ResponseEntity.badRequest().body(erro.getMessage());
        }
    }

    public ResponseEntity<?> sair(
            @RequestParam("id") final Long id
    ){
        try{
            final Movimentacao movimentacaoBanco = this.movimentacaoRepository.findById(id).orElse(null);

            this.movimentacaoService.sair(movimentacaoBanco);

        }catch (Exception erro){

            return ResponseEntity.badRequest().body(erro.);

        }
    }

}
