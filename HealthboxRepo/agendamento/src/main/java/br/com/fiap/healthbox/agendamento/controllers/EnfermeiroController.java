package br.com.fiap.healthbox.agendamento.controllers;

import br.com.fiap.healthbox.agendamento.services.EnfermeiroService;
import br.com.fiap.healthbox.dtos.EnfermeiroCreateDTO;
import br.com.fiap.healthbox.dtos.EnfermeiroResponseDTO;
import br.com.fiap.healthbox.dtos.EnfermeiroUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EnfermeiroController {

    private final EnfermeiroService enfermeiroService;

    public EnfermeiroController(EnfermeiroService enfermeiroService) {
        this.enfermeiroService = enfermeiroService;
    }

    @QueryMapping
    public Page<EnfermeiroResponseDTO> enfermeiros(@Argument Integer page, @Argument Integer size) {

        int pageNumber = page != null ? page : 0;
        int pageSize = size != null ? size : 10;

        return enfermeiroService.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @QueryMapping
    public EnfermeiroResponseDTO enfermeiro(@Argument Integer id) {
        return enfermeiroService.findById(id);
    }

    @MutationMapping
    public EnfermeiroResponseDTO createEnfermeiro(@Argument("input")
                                                  EnfermeiroCreateDTO enfermeiroCreateDTO) {
        return enfermeiroService.create(enfermeiroCreateDTO);
    }

    @MutationMapping
    public EnfermeiroResponseDTO updateEnfermeiro(@Argument Integer id, @Argument("input")
    EnfermeiroUpdateDTO enfermeiroUpdateDTO) {
        return enfermeiroService.update(id, enfermeiroUpdateDTO);
    }

    @MutationMapping
    public Boolean deleteEnfermeiro(@Argument Integer id) {
        return enfermeiroService.delete(id);
    }

}
