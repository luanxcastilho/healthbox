package br.com.fiap.healthbox.dtos;

import java.time.LocalDate;

public class AgendamentoUpdateDTO {
    
    Integer paciente;
    Integer medico;
    Integer enfermeiro;
    LocalDate dataAgendamento;
    String horaAgendamento;
    
    public Integer getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Integer paciente) {
        this.paciente = paciente;
    }
    
    public Integer getMedico() {
        return medico;
    }
    
    public void setMedico(Integer medico) {
        this.medico = medico;
    }
    
    public Integer getEnfermeiro() {
        return enfermeiro;
    }
    
    public void setEnfermeiro(Integer enfermeiro) {
        this.enfermeiro = enfermeiro;
    }
    
    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }
    
    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
    
    public String getHoraAgendamento() {
        return horaAgendamento;
    }
    
    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }
}
