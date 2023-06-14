package br.com.clientes.Clientes.Demo.controller;

import br.com.clientes.Clientes.Demo.model.dto.UsuarioPayloadDTO;
import br.com.clientes.Clientes.Demo.repository.UsuarioRepository;
import br.com.clientes.Clientes.Demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> getAllUsuarios() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }

    @PostMapping
    public ResponseEntity<Object> adicionarUsuario(@Valid @RequestBody UsuarioPayloadDTO usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioPayloadDTO usuario) {
        return usuarioService.update(usuario, id);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

}
