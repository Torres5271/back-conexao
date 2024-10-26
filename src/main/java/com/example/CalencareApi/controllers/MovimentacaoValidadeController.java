package com.example.CalencareApi.controllers;

import com.example.CalencareApi.dto.validade.movimentacao.MovimentacaoValidadeConsultaDto;
import com.example.CalencareApi.dto.validade.movimentacao.MovimentacaoValidadeCriacaoDto;
import com.example.CalencareApi.service.MovimentacaoValidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacao-validade")
public class MovimentacaoValidadeController {

    private final MovimentacaoValidadeService movimentacaoValidadeService;

    public MovimentacaoValidadeController(MovimentacaoValidadeService movimentacaoValidadeService) {
        this.movimentacaoValidadeService = movimentacaoValidadeService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoValidadeConsultaDto> cadastrar(
            @Valid @RequestBody
            MovimentacaoValidadeCriacaoDto movimentacaoValidadeCriacaoDto) {

        if (movimentacaoValidadeCriacaoDto.getIdValidade() == null) {
            return ResponseEntity.badRequest().build();
        }

        MovimentacaoValidadeConsultaDto mvConsultaDto =
                movimentacaoValidadeService.cadastrar(movimentacaoValidadeCriacaoDto);

        return ResponseEntity.ok(mvConsultaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoValidadeConsultaDto> buscarPorId(@PathVariable Integer id) {
        MovimentacaoValidadeConsultaDto mvConsultaDto = movimentacaoValidadeService.buscarPorId(id);
        if (mvConsultaDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mvConsultaDto);
    }

    @GetMapping("/validade/{id}")
    public ResponseEntity<List<MovimentacaoValidadeConsultaDto>> buscarPorValidadeId(@PathVariable Integer id) {
        List<MovimentacaoValidadeConsultaDto> mvConsultaDto = movimentacaoValidadeService.buscarPorValidadeId(id);
        if (mvConsultaDto == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mvConsultaDto);
    }

    @GetMapping("/quantidade/{idValidade}")
    public ResponseEntity<Integer> retornarQuantidadePorValidade(@PathVariable Integer idValidade) {
        Integer quantidade = movimentacaoValidadeService.retornarQuantidadePorValidade(idValidade);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/quantidade/produto/{idProduto}")
    public ResponseEntity<Integer> retornarQuantidadeTodasValidadesProduto(@PathVariable Integer idProduto) {
        Integer quantidade = movimentacaoValidadeService.retornarQuantidadeTodasValidadesProduto(idProduto);
        return ResponseEntity.ok(quantidade);
    }

}
