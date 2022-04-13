package com.vivo.minhasfinancas.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.vivo.minhasfinancas.model.entity.Lancamento;
import com.vivo.minhasfinancas.model.enums.StatusLancamento;
import com.vivo.minhasfinancas.model.enums.TipoLancamento;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class LancamentoRepositoryTest {

	@Autowired
	LancamentoRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveSalvarUmLancamento() {
		Lancamento lancamento = criarLancamento();
		
		Assertions.assertThat(lancamento.getId()).isNotNull();	
		
	}
	
	@Test
	public void deveDeletarUmLancamento() {
		Lancamento lancamento = criarEPersistirUmLancamento();
		
		entityManager.persist(lancamento);
		lancamento = entityManager.find(Lancamento.class, lancamento.getId());
		
		repository.delete(lancamento);
		
		Lancamento lancamentoInexistente = entityManager.find(Lancamento.class, lancamento.getId());
		Assertions.assertThat(lancamentoInexistente).isNull();
		
		
	}
	
	@Test
	public void deveAtualizarUmLancamento() {
		Lancamento lancamento = criarEPersistirUmLancamento();
		
		lancamento.setAno(2018);
		lancamento.setDescricao("Teste att");
		lancamento.setStatus(StatusLancamento.CANCELADO);
		
		Lancamento lancamentoAtualizado = entityManager.find(Lancamento.class, lancamento.getId());
		
		Assertions.assertThat(lancamentoAtualizado.getAno()).isEqualTo(2018);
		
		
	}
	
	@Test
	public void deveBuscarUmLancamentoPorId() {
		Lancamento lancamento = criarEPersistirUmLancamento();
		
		Optional<Lancamento> lancamentoEncontrado = repository.findById(lancamento.getId());
		
		Assertions.assertThat(lancamentoEncontrado.isPresent()).isTrue();
		
		
	}
	
	private Lancamento criarEPersistirUmLancamento() {
		Lancamento lancamento = criarLancamento();
		entityManager.persist(lancamento);
		return lancamento;
		
	}
	
	
	
	public static Lancamento criarLancamento() {
		return Lancamento.builder()
								.ano(2019)
								.mes(1)
								.descricao("lancamento qualquer")
								.valor(BigDecimal.valueOf(10))
								.tipo(TipoLancamento.RECEITA)
								.status(StatusLancamento.PENDENTE)
								.dataCadastro(LocalDate.now())
								.build();
	}

}
