package br.com.fiap.healthbox.agendamento.mappers;

import br.com.fiap.healthbox.agendamento.entities.Medico;
import br.com.fiap.healthbox.dtos.MedicoCreateDTO;
import br.com.fiap.healthbox.dtos.MedicoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {
    
    public static MedicoResponseDTO toDTO(Medico medico) {
        MedicoResponseDTO medicoResponseDTO = new MedicoResponseDTO();
        medicoResponseDTO.setId(medico.getId());
        medicoResponseDTO.setNome(medico.getNome());
        medicoResponseDTO.setCrm(medico.getCrm());
        medicoResponseDTO.setEspecialidade(medico.getEspecialidade());
        return medicoResponseDTO;
    }
    
    public static Medico toEntity(MedicoCreateDTO medicoCreateDTO) {
        Medico medico = new Medico();
        medico.setNome(medicoCreateDTO.getNome());
        medico.setCrm(medicoCreateDTO.getCrm());
        medico.setEspecialidade(medicoCreateDTO.getEspecialidade());
        return medico;
    }
    
    public static Medico toEntity(MedicoResponseDTO medicoResponseDTO) {
        Medico medico = new Medico();
        medico.setId(medicoResponseDTO.getId());
        medico.setNome(medicoResponseDTO.getNome());
        medico.setCrm(medicoResponseDTO.getCrm());
        medico.setEspecialidade(medicoResponseDTO.getEspecialidade());
        return medico;
    }
    
    
}
