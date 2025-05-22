package br.com.fiap.healthbox.dtos;

import java.time.LocalDate;

public class AgendamentoResponseDTO {
    
    Integer id;
    PacienteResponseDTO paciente;
    MedicoResponseDTO medico;
    EnfermeiroResponseDTO enfermeiro;
    LocalDate dataAgendamento;
    String horaAgendamento;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public PacienteResponseDTO getPaciente() {
        return paciente;
    }
    
    public void setPaciente(PacienteResponseDTO paciente) {
        this.paciente = paciente;
    }
    
    public MedicoResponseDTO getMedico() {
        return medico;
    }
    
    public void setMedico(MedicoResponseDTO medico) {
        this.medico = medico;
    }
    
    public EnfermeiroResponseDTO getEnfermeiro() {
        return enfermeiro;
    }
    
    public void setEnfermeiro(EnfermeiroResponseDTO enfermeiro) {
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
    
    @Override
    public String toString() {
        return "AgendamentoResponseDTO [id=" + id + ", paciente=" + paciente.id + ", medico=" + medico.id + ", enfermeiro="
                + enfermeiro.id + ", dataAgendamento=" + dataAgendamento + ", horaAgendamento=" + horaAgendamento + "]";
    }
}
