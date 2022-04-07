package com.vivo.minhasfinancas.service;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vivo.minhasfinancas.exception.RegraNegocioException;
import com.vivo.minhasfinancas.model.entity.Usuario;
import com.vivo.minhasfinancas.model.repository.UsuarioRepository;
import com.vivo.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
		
	
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@BeforeEach
	public void setUp() {
		service = new UsuarioServiceImpl(repository);
		
		
	}
	
	@Test
	public void deveAutenticarUmUsuarioComSucesso() {
		//cenario
		String email = "email@email.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//ação
		Usuario result = service.autenticar(email, senha);
		
		//verificação
		Assertions.assertThat(result).isNotNull();
		
		
		
	}
	
	
	@Test
	public void deveValidarEmail() {
		
			
			UsuarioRepository usuarioRepositoryMock = Mockito.mock(UsuarioRepository.class);
 
			// cenario
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
			// acao
			service.validarEmail("email@email.com");
		
	}
 
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
			//cenario
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
 
			//acao
			org.junit.jupiter.api.Assertions
				.assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
		
	}
}
