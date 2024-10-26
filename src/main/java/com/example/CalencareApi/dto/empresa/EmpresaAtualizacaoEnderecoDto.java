package com.example.CalencareApi.dto.empresa;

import jakarta.validation.constraints.NotBlank;

public class EmpresaAtualizacaoEnderecoDto {
    @NotBlank
    private String cep;
    @NotBlank
    private String numero;
    private String complemento;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
