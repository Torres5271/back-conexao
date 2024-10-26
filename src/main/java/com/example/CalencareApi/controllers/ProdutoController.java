package com.example.CalencareApi.controllers;

import com.example.CalencareApi.dto.produto.ProdutoAtualizarDto;
import com.example.CalencareApi.dto.produto.ProdutoConsultaDto;
import com.example.CalencareApi.dto.produto.ProdutoCriacaoDto;
import com.example.CalencareApi.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoConsultaDto> cadastrar(@Valid @RequestBody ProdutoCriacaoDto dto) {
        if (Objects.isNull(dto)) { return ResponseEntity.status(400).build(); }
        ProdutoConsultaDto savedDto = produtoService.cadastrar(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{idEmpresa}/{id}")
    public ResponseEntity<ProdutoConsultaDto> buscarPorId(@PathVariable Integer idEmpresa,
                                                          @PathVariable Integer id) {
        ProdutoConsultaDto dto = produtoService.buscarPorIdPorEmpresa(id, idEmpresa);
        if (Objects.isNull(dto)) { return ResponseEntity.status(404).build(); }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{idEmpresa}/{idProduto}")
    public ResponseEntity<ProdutoConsultaDto> atualizar(@PathVariable Integer idEmpresa,
                                                        @PathVariable Integer idProduto,
                                                        @Valid @RequestBody ProdutoAtualizarDto dto) {
        if (Objects.isNull(dto)) { return ResponseEntity.status(400).build(); }
        ProdutoConsultaDto updatedDto = produtoService.atualizar(dto, idProduto, idEmpresa);
        if (Objects.isNull(updatedDto)) { return ResponseEntity.status(404).build(); }
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{idEmpresa}/{idProduto}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idEmpresa,
                                        @PathVariable Integer idProduto) {
        produtoService.deletarPorId(idProduto, idEmpresa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idEmpresa}/buscar-nome")
    public ResponseEntity<List<ProdutoConsultaDto>> buscarPorNomeOuMarca(@PathVariable Integer idEmpresa,
                                                                  @RequestParam String nome) {
        List<ProdutoConsultaDto> produtos = produtoService.buscarPorNomeOuMarca(idEmpresa, nome);
        if (produtos.isEmpty()) { return ResponseEntity.status(204).build(); }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idEmpresa}/buscar-todos")
    public ResponseEntity<List<ProdutoConsultaDto>> buscarTodos(@PathVariable Integer idEmpresa) {
        List<ProdutoConsultaDto> produtos = produtoService.listarPorEmpresaId(idEmpresa);
        if (produtos.isEmpty()) { return ResponseEntity.status(204).build(); }
        return ResponseEntity.ok(produtos);
    }
}
