package br.com.fiap.healthbox.agendamento.controllers;

import br.com.fiap.healthbox.agendamento.services.MedicoService;
import br.com.fiap.healthbox.dtos.MedicoCreateDTO;
import br.com.fiap.healthbox.dtos.MedicoResponseDTO;
import br.com.fiap.healthbox.dtos.MedicoUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MedicoController {
    
    private final MedicoService medicoService;
    
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    
    @QueryMapping
    public Page<MedicoResponseDTO> medicos(@Argument Integer page, @Argument Integer size) {
        
        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        
        return medicoService.findAll(PageRequest.of(pageNumber, pageSize));
    }
    
    @QueryMapping
    public MedicoResponseDTO medico(@Argument Integer id) {
        return medicoService.findById(id);
    }
    
    @MutationMapping
    public MedicoResponseDTO createMedico(@Argument("input")
                                          MedicoCreateDTO medicoCreateDTO) {
        return medicoService.create(medicoCreateDTO);
    }
    
    @MutationMapping
    public MedicoResponseDTO updateMedico(@Argument Integer id, @Argument("input")
    MedicoUpdateDTO medicoUpdateDTO) {
        return medicoService.update(id, medicoUpdateDTO);
    }
    
    @MutationMapping
    public Boolean deleteMedico(@Argument Integer id) {
        return medicoService.delete(id);
    }
}
