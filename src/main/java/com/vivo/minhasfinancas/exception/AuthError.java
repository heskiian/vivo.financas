package com.vivo.minhasfinancas.exception;

public class AuthError extends RuntimeException {
	
	public AuthError(String mensagem) {
	super(mensagem);
	}
}
