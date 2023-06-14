package br.com.clientes.Clientes.Demo.repository;

import br.com.clientes.Clientes.Demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuarios WHERE hierarquia = :id", nativeQuery = true)
    List<Usuario> findFuncionariosByIdSuperior(@Param("id") Long id);
}
