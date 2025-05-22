package br.com.fiap.healthbox.agendamento.exceptions;

public class AgendamentoNotFoundException extends RuntimeException {
    public AgendamentoNotFoundException(String message) {
        super(message);
    }
}
