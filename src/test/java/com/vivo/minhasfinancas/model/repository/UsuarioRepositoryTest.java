package com.vivo.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vivo.minhasfinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	// deve verificar a existencia de um email
	public void VerificaExistenciaDeEmail() {
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		boolean result = repository.existsByEmail("usuario@email.com");
		
		Assertions.assertThat(result).isTrue();
		
	}




	@Test
	//deve retornar falso quando nao houver um usuario cadastrado com o email
	public void deveRetornarFalso() {
		
		
		//ação
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
		Assertions.assertThat(result).isFalse();
		
		
	}
	
	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		
		//cenário
		Usuario usuario = criarUsuario();
		
		//ação
		Usuario usuarioSalvo = repository.save(usuario);
		
		//validação
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
		
	}
	
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
				
		//verificação
	    Optional<Usuario> result = repository.findByEmail("usuario@email.com");
				
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	// deve retornar vazio ao buscar um usuario por email quando nao existir na base
	public void deveRetornarVazio() {
		//cenário
		
				
		//verificação
	    Optional<Usuario> result = repository.findByEmail("usuario@email.com");
				
		Assertions.assertThat(result.isPresent()).isFalse();
	}
	
	
	public static Usuario criarUsuario() {
			return Usuario
			
				.builder()
				.nome("usuario")
				.email("usuario@email.com")
				.senha("senha")
				.build();
	}
}
	
	