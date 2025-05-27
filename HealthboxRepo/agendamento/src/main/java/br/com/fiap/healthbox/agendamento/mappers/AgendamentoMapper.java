package br.com.fiap.healthbox.agendamento.mappers;

import br.com.fiap.healthbox.agendamento.entities.Agendamento;
import br.com.fiap.healthbox.agendamento.services.EnfermeiroService;
import br.com.fiap.healthbox.agendamento.services.MedicoService;
import br.com.fiap.healthbox.agendamento.services.PacienteService;
import br.com.fiap.healthbox.dtos.AgendamentoCreateDTO;
import br.com.fiap.healthbox.dtos.AgendamentoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {
    
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final EnfermeiroService enfermeiroService;
    
    public AgendamentoMapper(
            PacienteService pacienteService,
            MedicoService medicoService,
            EnfermeiroService enfermeiroService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.enfermeiroService = enfermeiroService;
    }
    
    public Agendamento toEntity(AgendamentoCreateDTO agendamentoCreateDTO) {
        Agendamento agendamento = new Agendamento();
        agendamento.setPaciente(PacienteMapper.toEntity(pacienteService.findById(agendamentoCreateDTO.getPaciente())));
        agendamento.setMedico(MedicoMapper.toEntity(medicoService.findById(agendamentoCreateDTO.getMedico())));
        agendamento.setEnfermeiro(EnfermeiroMapper.toEntity(enfermeiroService.findById(agendamentoCreateDTO.getEnfermeiro())));
        agendamento.setDataAgendamento(agendamentoCreateDTO.getDataAgendamento());
        agendamento.setHoraAgendamento(agendamentoCreateDTO.getHoraAgendamento());
        return agendamento;
    }
    
    public AgendamentoResponseDTO toDTO(Agendamento agendamento) {
        AgendamentoResponseDTO agendamentoResponseDTO = new AgendamentoResponseDTO();
        agendamentoResponseDTO.setId(agendamento.getId());
        agendamentoResponseDTO.setPaciente(PacienteMapper.toDTO(agendamento.getPaciente()));
        agendamentoResponseDTO.setMedico(MedicoMapper.toDTO(agendamento.getMedico()));
        agendamentoResponseDTO.setEnfermeiro(EnfermeiroMapper.toDTO(agendamento.getEnfermeiro()));
        agendamentoResponseDTO.setDataAgendamento(agendamento.getDataAgendamento());
        agendamentoResponseDTO.setHoraAgendamento(agendamento.getHoraAgendamento());
        return agendamentoResponseDTO;
    }
    
    
}
