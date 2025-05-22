package br.com.fiap.healthbox.agendamento.mappers;

import br.com.fiap.healthbox.agendamento.entities.Enfermeiro;
import br.com.fiap.healthbox.dtos.EnfermeiroCreateDTO;
import br.com.fiap.healthbox.dtos.EnfermeiroResponseDTO;

public class EnfermeiroMapper {
    
    
    
    public static EnfermeiroResponseDTO toDTO(Enfermeiro enfermeiro){
        EnfermeiroResponseDTO enfermeiroResponse = new EnfermeiroResponseDTO();
        enfermeiroResponse.setId(enfermeiro.getId());
        enfermeiroResponse.setNome(enfermeiro.getNome());
        enfermeiroResponse.setCoren(enfermeiro.getCoren());
        return enfermeiroResponse;
    };
    
    public static Enfermeiro toEntity(EnfermeiroCreateDTO enfermeiroCreateDTO){
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setNome(enfermeiroCreateDTO.getNome());
        enfermeiro.setCoren(enfermeiroCreateDTO.getCoren());
        return enfermeiro;
    };
    
    public static Enfermeiro toEntity(EnfermeiroResponseDTO enfermeiroResponseDTO){
        Enfermeiro enfermeiro = new Enfermeiro();
        enfermeiro.setId(enfermeiroResponseDTO.getId());
        enfermeiro.setNome(enfermeiroResponseDTO.getNome());
        enfermeiro.setCoren(enfermeiroResponseDTO.getCoren());
        return enfermeiro;
    };
}
