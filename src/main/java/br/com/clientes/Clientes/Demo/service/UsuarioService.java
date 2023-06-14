package br.com.clientes.Clientes.Demo.service;

import br.com.clientes.Clientes.Demo.model.Senha;
import br.com.clientes.Clientes.Demo.model.Usuario;
import br.com.clientes.Clientes.Demo.model.dto.UsuarioPayloadDTO;
import br.com.clientes.Clientes.Demo.model.dto.UsuarioResponseDTO;
import br.com.clientes.Clientes.Demo.model.dto.UsuarioResponseSimpleDTO;
import br.com.clientes.Clientes.Demo.repository.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ResponseEntity<Object> save(UsuarioPayloadDTO payload){
        if(!validate(payload)){
            return new ResponseEntity<>("Usuário informado como superior hierárquico, não existe.",
                    HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = Usuario.builder()
                .nome(payload.getNome())
                .hierarquia(payload.getHierarquia())
                .senha(encryptPassword(payload.getSenha()))
                .build();

        usuario.setForcaSenha(verificarForcaSenha(payload.getSenha()));
        Usuario usuarioPersisted = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioPersisted.toResponse(usuarioPersisted), HttpStatus.OK);
    }

    public ResponseEntity<Object> update(UsuarioPayloadDTO payload, Long id){
        if(!validate(payload)){
            return new ResponseEntity<>("Usuário informado como superior hierárquico, não existe.",
                    HttpStatus.BAD_REQUEST);
        }
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){

            Usuario usuario = Usuario.builder()
                    .id(id)
                    .nome(payload.getNome())
                    .hierarquia(payload.getHierarquia())
                    .senha(encryptPassword(payload.getSenha()))
                    .forcaSenha(optional.get().getForcaSenha())
                    .build();

            usuarioRepository.save(usuario);

            return new ResponseEntity<>(buildToResponseDTO(usuario), HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> getAllUsers(){
        List<Usuario> usuarios =  usuarioRepository.findAll();
        if(usuarios.size() > 0){
            List<UsuarioResponseSimpleDTO> dtos = new ArrayList<>();
            usuarios.stream().forEach(usuario -> {
                dtos.add(buildToResponseSimpleDTO(usuario));
            });
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>("Nenhum usuário encontrado.",HttpStatus.BAD_REQUEST);
    }

    public boolean validate(UsuarioPayloadDTO payload){
        if(payload.getHierarquia() != null && !userIsValid(payload.getHierarquia())){
            return false;
        }
        return true;
    }

    public boolean userIsValid(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent();
    }

    private String encryptPassword(String pwd){
        return BCrypt.hashpw(pwd, BCrypt.gensalt());
    }

    public ResponseEntity<Object> getUsuario(Long id) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if(optional.isPresent()){
            return new ResponseEntity<>(buildToResponseDTO(optional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Usuário não encontrado", HttpStatus.BAD_REQUEST);
    }

    private UsuarioResponseDTO buildToResponseDTO(Usuario usuario){

        List<Usuario> usuarios = usuarioRepository.findFuncionariosByIdSuperior(usuario.getId());

        return UsuarioResponseDTO
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .forcaSenha(usuario.getForcaSenha())
                .funcionarios(usuarios.stream().map(m -> buildToResponseSimpleDTO(m)).collect(Collectors.toList()))
                .build();
    }

    private UsuarioResponseSimpleDTO buildToResponseSimpleDTO(Usuario usuario){
        return UsuarioResponseSimpleDTO
                .builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .forcaSenha(usuario.getForcaSenha())
                .hierarquia(usuario.getHierarquia())
                .build();
    }

    public String verificarForcaSenha(String senha) {
        // Regras para verificar a força da senha
        if (senha.length() < 8) {
            return Senha.FRACA.toString();
        }

        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temCaracterEspecial = false;

        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) {
                temMaiuscula = true;
            } else if (Character.isLowerCase(c)) {
                temMinuscula = true;
            } else if (Character.isDigit(c)) {
                temNumero = true;
            } else {
                temCaracterEspecial = true;
            }
        }

        // Avaliação da força da senha com base nas regras definidas
        if (temMaiuscula && temMinuscula && temNumero && temCaracterEspecial) {
            return Senha.MUITO_FORTE.toString();
        } else if (temMaiuscula && temMinuscula && temNumero) {
            return Senha.FORTE.toString();
        } else if ((temMaiuscula || temMinuscula) && temNumero) {
            return Senha.MODERADA.toString();
        } else {
            return Senha.FRACA.toString();
        }
    }


}
