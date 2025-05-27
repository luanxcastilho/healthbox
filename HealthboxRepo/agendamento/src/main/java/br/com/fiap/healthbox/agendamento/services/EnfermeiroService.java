package br.com.fiap.healthbox.agendamento.services;

import br.com.fiap.healthbox.agendamento.entities.Enfermeiro;
import br.com.fiap.healthbox.agendamento.exceptions.EnfermeiroNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.EnfermeiroPossuiAgendamentoException;
import br.com.fiap.healthbox.agendamento.mappers.EnfermeiroMapper;
import br.com.fiap.healthbox.agendamento.repositories.EnfermeiroRepository;
import br.com.fiap.healthbox.dtos.EnfermeiroCreateDTO;
import br.com.fiap.healthbox.dtos.EnfermeiroResponseDTO;
import br.com.fiap.healthbox.dtos.EnfermeiroUpdateDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnfermeiroService {

    private final EnfermeiroRepository enfermeiroRepository;

    public EnfermeiroService(EnfermeiroRepository enfermeiroRepository) {
        this.enfermeiroRepository = enfermeiroRepository;
    }

    public Page<EnfermeiroResponseDTO> findAll(Pageable pageable) {
        return enfermeiroRepository.findAll(pageable).map(EnfermeiroMapper::toDTO);
    }

    public EnfermeiroResponseDTO findById(int id) {
        Enfermeiro enfermeiro = enfermeiroRepository.findById(id)
                .orElseThrow(() -> new EnfermeiroNotFoundException("Enfermeiro com ID "+id+" não encontrado"));
        
        return EnfermeiroMapper.toDTO(enfermeiro);
    }

    public EnfermeiroResponseDTO create(EnfermeiroCreateDTO enfermeiroCreateDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        
        Enfermeiro enfermeiro = EnfermeiroMapper.toEntity(enfermeiroCreateDTO);
        enfermeiro.setDataAlteracao(dataAtual);
        enfermeiro.setDataInclusao(dataAtual);
        
        Enfermeiro enfermeiroCriado = enfermeiroRepository.save(enfermeiro);
        return EnfermeiroMapper.toDTO(enfermeiroCriado);
    }

    public EnfermeiroResponseDTO update(Integer id, EnfermeiroUpdateDTO enfermeiroUpdateDTO) {

        Enfermeiro enfermeiro = enfermeiroRepository.findById(id)
                .orElseThrow(() -> new EnfermeiroNotFoundException("Enfermeiro com ID "+id+" não encontrado"));
        
        if (enfermeiroUpdateDTO.getNome() != null && !enfermeiroUpdateDTO.getNome().equals(enfermeiro.getNome()))
            enfermeiro.setNome(enfermeiroUpdateDTO.getNome());
        
        if (enfermeiroUpdateDTO.getCoren() != null && !enfermeiroUpdateDTO.getCoren().equals(enfermeiro.getCoren()))
            enfermeiro.setCoren(enfermeiroUpdateDTO.getCoren());
        
        enfermeiro.setDataAlteracao(LocalDateTime.now());

        Enfermeiro enfermeiroAtualizado = enfermeiroRepository.save(enfermeiro);
        return EnfermeiroMapper.toDTO(enfermeiroAtualizado);
    }

    public Boolean delete(Integer id) {
        Optional<Enfermeiro> enfermeiro = enfermeiroRepository.findById(id);

        if (enfermeiro.isPresent()) {
            try {
                enfermeiroRepository.deleteById(id);
                return true;
                
            } catch (DataIntegrityViolationException e) {
                throw new EnfermeiroPossuiAgendamentoException("Enfermeiro com ID " + id + " está em uso e não pode ser removido.");
            }
        }
        return false;
    }

}
