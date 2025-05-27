package br.com.fiap.healthbox.agendamento.controllers;

import br.com.fiap.healthbox.agendamento.services.AgendamentoService;
import br.com.fiap.healthbox.dtos.AgendamentoCreateDTO;
import br.com.fiap.healthbox.dtos.AgendamentoResponseDTO;
import br.com.fiap.healthbox.dtos.AgendamentoUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AgendamentoController {
    
    private final AgendamentoService agendamentoService;
    
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }
    
    @QueryMapping
    public Page<AgendamentoResponseDTO> agendamentos(@Argument Integer page, @Argument Integer size) {
        
        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        
        return agendamentoService.findAll(PageRequest.of(pageNumber, pageSize));
    }
    
    @QueryMapping
    public AgendamentoResponseDTO agendamento(@Argument Integer id) {
        return agendamentoService.findById(id);
    }
    
    @QueryMapping
    public Page<AgendamentoResponseDTO> agendamentoPorPaciente(@Argument Integer id, @Argument Integer page, @Argument Integer size) {
        
        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;
        
        return agendamentoService.findByPacienteId(id, PageRequest.of(pageNumber, pageSize));
    }
    
    @MutationMapping
    public AgendamentoResponseDTO createAgendamento(@Argument("input")
                                                    AgendamentoCreateDTO agendamentoCreateDTO) {
        return agendamentoService.create(agendamentoCreateDTO);
    }
    
    @MutationMapping
    public AgendamentoResponseDTO updateAgendamento(@Argument Integer id, @Argument("input")
    AgendamentoUpdateDTO agendamentoUpdateDTO) {
        return agendamentoService.update(id, agendamentoUpdateDTO);
    }
    
    @MutationMapping
    public Boolean deleteAgendamento(@Argument Integer id) {
        return agendamentoService.delete(id);
    }
    
}
