package br.com.fiap.healthbox.agendamento.controllers;

import br.com.fiap.healthbox.agendamento.services.PacienteService;
import br.com.fiap.healthbox.dtos.PacienteCreateDTO;
import br.com.fiap.healthbox.dtos.PacienteResponseDTO;
import br.com.fiap.healthbox.dtos.PacienteUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @QueryMapping
    public Page<PacienteResponseDTO> pacientes(@Argument Integer page, @Argument Integer size) {

        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;

        return pacienteService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @QueryMapping
    public PacienteResponseDTO paciente(@Argument Integer id) {
        return pacienteService.findById(id);
    }

    @MutationMapping
    public PacienteResponseDTO createPaciente(@Argument("input")
                                              PacienteCreateDTO pacienteCreateDTO) {
        return pacienteService.create(pacienteCreateDTO);
    }

    @MutationMapping
    public PacienteResponseDTO updatePaciente(@Argument Integer id, @Argument("input")
    PacienteUpdateDTO pacienteUpdateDTO) {
        return pacienteService.update(id, pacienteUpdateDTO);
    }

    @MutationMapping
    public Boolean deletePaciente(@Argument Integer id) {
        return pacienteService.delete(id);
    }

}
