package br.com.fiap.healthbox.agendamento.exceptions;

public class EnfermeiroNotFoundException extends RuntimeException {
    public EnfermeiroNotFoundException(String message) {
        super(message);
    }
}
