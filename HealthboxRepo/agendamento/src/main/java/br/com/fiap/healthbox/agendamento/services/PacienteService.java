package br.com.fiap.healthbox.agendamento.services;

import br.com.fiap.healthbox.agendamento.entities.Paciente;
import br.com.fiap.healthbox.agendamento.exceptions.MedicoPossuiAgendamentoException;
import br.com.fiap.healthbox.agendamento.exceptions.PacienteNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.PacientePossuiAgendamentoException;
import br.com.fiap.healthbox.agendamento.mappers.PacienteMapper;
import br.com.fiap.healthbox.agendamento.repositories.PacienteRepository;
import br.com.fiap.healthbox.dtos.PacienteCreateDTO;
import br.com.fiap.healthbox.dtos.PacienteResponseDTO;
import br.com.fiap.healthbox.dtos.PacienteUpdateDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PacienteService implements Serializable {
    
    private final PacienteRepository pacienteRepository;
    
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    public Page<PacienteResponseDTO> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable).map(PacienteMapper::toDTO);
    }
    
    public PacienteResponseDTO findById(int id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente com ID "+id+" não encontrado"));
        
        return PacienteMapper.toDTO(paciente);
    }
    
    public PacienteResponseDTO create(PacienteCreateDTO pacienteCreateDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        
        Paciente paciente = PacienteMapper.toEntity(pacienteCreateDTO);
        paciente.setDataAlteracao(dataAtual);
        paciente.setDataInclusao(dataAtual);
        
        Paciente pacienteCriado = pacienteRepository.save(paciente);
        return PacienteMapper.toDTO(pacienteCriado);
    }
    
    public PacienteResponseDTO update(Integer id, PacienteUpdateDTO pacienteUpdateDTO) {
        
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente com ID "+id+" não encontrado"));
        
        if (pacienteUpdateDTO.getNome() != null && !pacienteUpdateDTO.getNome().equals(paciente.getNome()))
            paciente.setNome(pacienteUpdateDTO.getNome());
        
        if (pacienteUpdateDTO.getCpf() != null && !pacienteUpdateDTO.getCpf().equals(paciente.getCpf()))
            paciente.setCpf(pacienteUpdateDTO.getCpf());
        
        if (pacienteUpdateDTO.getDataNascimento() != null && !pacienteUpdateDTO.getDataNascimento().equals(paciente.getDataNascimento()))
            paciente.setDataNascimento(pacienteUpdateDTO.getDataNascimento());
        
        paciente.setDataAlteracao(LocalDateTime.now());
        
        Paciente pacienteAtualizado = pacienteRepository.save(paciente);
        return PacienteMapper.toDTO(pacienteAtualizado);
    }
    
    public Boolean delete(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        
        if (paciente.isPresent()) {
            try {
                pacienteRepository.deleteById(id);
                return true;
            }catch (DataIntegrityViolationException e) {
                throw new PacientePossuiAgendamentoException("Paciente com ID " + id + " está em uso e não pode ser removido.");
            }
        }
        return false;
    }
    
}
