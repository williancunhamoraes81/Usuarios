package br.com.clientes.Clientes.Demo.model;

import br.com.clientes.Clientes.Demo.model.dto.UsuarioResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("senha")
    private String senha;

    @JsonProperty("forca_senha")
    private String forcaSenha;

    @JsonProperty("hierarquia")
    private Long hierarquia;

    public UsuarioResponseDTO toResponse(Usuario usuario){
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .forcaSenha(usuario.forcaSenha)
                .build();
    }

}
