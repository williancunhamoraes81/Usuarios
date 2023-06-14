package br.com.clientes.Clientes.Demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseSimpleDTO {

    private Long id;
    private String nome;
    private String forcaSenha;
    private Long hierarquia;

}
