package br.com.fiap.healthbox.agendamento.services;

import br.com.fiap.healthbox.agendamento.entities.Medico;
import br.com.fiap.healthbox.agendamento.exceptions.EnfermeiroPossuiAgendamentoException;
import br.com.fiap.healthbox.agendamento.exceptions.MedicoNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.MedicoPossuiAgendamentoException;
import br.com.fiap.healthbox.agendamento.mappers.MedicoMapper;
import br.com.fiap.healthbox.agendamento.repositories.MedicoRepository;
import br.com.fiap.healthbox.dtos.MedicoCreateDTO;
import br.com.fiap.healthbox.dtos.MedicoResponseDTO;
import br.com.fiap.healthbox.dtos.MedicoUpdateDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MedicoService {
    
    private final MedicoRepository medicoRepository;
    
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    
    public Page<MedicoResponseDTO> findAll(Pageable pageable) {
        return medicoRepository.findAll(pageable).map(MedicoMapper::toDTO);
    }
    
    public MedicoResponseDTO findById(int id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException("Médico com ID "+id+" não encontrado"));
        
        return MedicoMapper.toDTO(medico);
    }
    
    public MedicoResponseDTO create(MedicoCreateDTO medicoCreateDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        
        Medico medico = MedicoMapper.toEntity(medicoCreateDTO);
        medico.setDataAlteracao(dataAtual);
        medico.setDataInclusao(dataAtual);
        
        Medico medicoCriado = medicoRepository.save(medico);
        return MedicoMapper.toDTO(medicoCriado);
    }
    
    public MedicoResponseDTO update(Integer id, MedicoUpdateDTO medicoUpdateDTO) {
        
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException("Médico com ID "+id+" não encontrado"));
        
        if (medicoUpdateDTO.getNome() != null && !medicoUpdateDTO.getNome().equals(medico.getNome()))
            medico.setNome(medicoUpdateDTO.getNome());
        
        if (medicoUpdateDTO.getCrm() != null && !medicoUpdateDTO.getCrm().equals(medico.getCrm()))
            medico.setCrm(medicoUpdateDTO.getCrm());
        
        if (medicoUpdateDTO.getEspecialidade() != null && !medicoUpdateDTO.getEspecialidade().equals(medico.getEspecialidade()))
            medico.setEspecialidade(medicoUpdateDTO.getEspecialidade());
        
        medico.setDataAlteracao(LocalDateTime.now());
        
        Medico medicoAtualizado = medicoRepository.save(medico);
        return MedicoMapper.toDTO(medicoAtualizado);
    }
    
    public Boolean delete(Integer id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        
        if (medico.isPresent()) {
            try {
                medicoRepository.deleteById(id);
                return true;
            }
            catch (DataIntegrityViolationException e) {
                throw new MedicoPossuiAgendamentoException("Médico com ID " + id + " está em uso e não pode ser removido.");
            }
        }
        return false;
    }
    
}
