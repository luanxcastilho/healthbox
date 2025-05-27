package br.com.fiap.healthbox.agendamento.mappers;

import br.com.fiap.healthbox.agendamento.entities.Paciente;
import br.com.fiap.healthbox.dtos.PacienteCreateDTO;
import br.com.fiap.healthbox.dtos.PacienteResponseDTO;

public class PacienteMapper {

    public static PacienteResponseDTO toDTO(Paciente paciente) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
        pacienteResponseDTO.setId(paciente.getId());
        pacienteResponseDTO.setNome(paciente.getNome());
        pacienteResponseDTO.setCpf(paciente.getCpf());
        pacienteResponseDTO.setDataNascimento(paciente.getDataNascimento());
        return pacienteResponseDTO;
    }
    
    public static Paciente toEntity(PacienteCreateDTO pacienteCreateDTO) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteCreateDTO.getNome());
        paciente.setCpf(pacienteCreateDTO.getCpf());
        paciente.setDataNascimento(pacienteCreateDTO.getDataNascimento());
        return paciente;
    }
    
    public static Paciente toEntity(PacienteResponseDTO pacienteResponseDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteResponseDTO.getId());
        paciente.setNome(pacienteResponseDTO.getNome());
        paciente.setCpf(pacienteResponseDTO.getCpf());
        paciente.setDataNascimento(pacienteResponseDTO.getDataNascimento());
        return paciente;
    }
}
