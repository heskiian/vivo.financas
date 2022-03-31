package com.vivo.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vivo.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
