package br.com.clientes.Clientes.Demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioPayloadDTO {

    @NotBlank(message = "O campo nome é informação obrigatória")
    private String nome;

    @NotBlank(message = "O campo senha é informação obrigatória")
    private String senha;

    private Long hierarquia;


}
