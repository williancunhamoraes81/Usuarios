package br.com.clientes.Clientes.Demo.model.dto;

import br.com.clientes.Clientes.Demo.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private List<UsuarioResponseSimpleDTO> funcionarios;
    private String forcaSenha;

}
