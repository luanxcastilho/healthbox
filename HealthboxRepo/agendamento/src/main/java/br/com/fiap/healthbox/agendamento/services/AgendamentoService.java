package br.com.fiap.healthbox.agendamento.services;

import br.com.fiap.healthbox.agendamento.entities.Agendamento;
import br.com.fiap.healthbox.agendamento.exceptions.AgendamentoNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.EnfermeiroNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.MedicoNotFoundException;
import br.com.fiap.healthbox.agendamento.exceptions.PacienteNotFoundException;
import br.com.fiap.healthbox.agendamento.mappers.AgendamentoMapper;
import br.com.fiap.healthbox.agendamento.repositories.AgendamentoRepository;
import br.com.fiap.healthbox.agendamento.repositories.EnfermeiroRepository;
import br.com.fiap.healthbox.agendamento.repositories.MedicoRepository;
import br.com.fiap.healthbox.agendamento.repositories.PacienteRepository;
import br.com.fiap.healthbox.dtos.AgendamentoCreateDTO;
import br.com.fiap.healthbox.dtos.AgendamentoResponseDTO;
import br.com.fiap.healthbox.dtos.AgendamentoUpdateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AgendamentoService {
    
    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final EnfermeiroRepository enfermeiroRepository;
    private final KafkaTemplate kafkaTemplate;
    
    private static final Logger log = LoggerFactory.getLogger(AgendamentoService.class);
    
    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              AgendamentoMapper agendamentoMapper,
                              MedicoRepository medicoRepository,
                              PacienteRepository pacienteRepository,
                              EnfermeiroRepository enfermeiroRepository,
                              KafkaTemplate kafkaTemplate) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.enfermeiroRepository = enfermeiroRepository;
        this.kafkaTemplate = kafkaTemplate;
    }
    
    public Page<AgendamentoResponseDTO> findAll(Pageable pageable) {
        return agendamentoRepository.findAll(pageable).map(agendamentoMapper::toDTO);
    }
    
    public AgendamentoResponseDTO findById(Integer id) {
        
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNotFoundException("Agendamento com ID " + id + " não encontrado"));
        
        return agendamentoMapper.toDTO(agendamento);
    }
    
    public AgendamentoResponseDTO create(AgendamentoCreateDTO agendamentoCreateDTO) {
        LocalDateTime dataAtual = LocalDateTime.now();
        
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoCreateDTO);
        agendamento.setDataAlteracao(dataAtual);
        agendamento.setDataInclusao(dataAtual);
        
        AgendamentoResponseDTO agendamentoCriado = agendamentoMapper.toDTO(agendamentoRepository.save(agendamento));
        log.info("Agendamento criado com sucesso: {}", agendamentoCriado);
        
        kafkaTemplate.send("agendamento-criado", agendamentoCriado);
        log.info("Fila enviada para o topico agendamento-criado: {}", agendamentoCriado);
        
        return agendamentoCriado;
    }
    
    public AgendamentoResponseDTO update( Integer id, AgendamentoUpdateDTO agendamentoUpdateDTO) {
        
        LocalDateTime dataAtual = LocalDateTime.now();
        
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoNotFoundException("Agendamento com ID " + id + " não encontrado"));
        
        if (agendamentoUpdateDTO.getPaciente() != null && !agendamentoUpdateDTO.getPaciente().equals(agendamento.getPaciente().getId())){
            
            agendamento.setPaciente(pacienteRepository.findById(agendamentoUpdateDTO.getPaciente())
                                          .orElseThrow(() -> new PacienteNotFoundException("Paciente com ID " + id + " não encontrado")));
        }
        
        if (agendamentoUpdateDTO.getMedico() != null && !agendamentoUpdateDTO.getMedico().equals(agendamento.getMedico().getId())) {
            
            agendamento.setMedico(medicoRepository.findById(agendamentoUpdateDTO.getMedico())
                                          .orElseThrow(() -> new MedicoNotFoundException("Médico com ID " + id + " não encontrado")));
        }
        
        if (agendamentoUpdateDTO.getEnfermeiro() != null && !agendamentoUpdateDTO.getEnfermeiro().equals(agendamento.getEnfermeiro().getId())) {
            
            agendamento.setEnfermeiro(enfermeiroRepository.findById(agendamentoUpdateDTO.getEnfermeiro())
                                          .orElseThrow(() -> new EnfermeiroNotFoundException("Enfermeiro com ID " + id + " não encontrado")));
        }
        
        if (agendamentoUpdateDTO.getDataAgendamento() != null && !agendamentoUpdateDTO.getDataAgendamento().equals(agendamento.getDataAgendamento()))
            agendamento.setDataAgendamento(agendamentoUpdateDTO.getDataAgendamento());
        
        if (agendamentoUpdateDTO.getHoraAgendamento() != null && !agendamentoUpdateDTO.getHoraAgendamento().equals(agendamento.getHoraAgendamento()))
            agendamento.setHoraAgendamento(agendamentoUpdateDTO.getHoraAgendamento());
        
        agendamento.setDataAlteracao(dataAtual);
        
        AgendamentoResponseDTO agendamentoAtualizado = agendamentoMapper.toDTO(agendamentoRepository.save(agendamento));
        log.info("Agendamento atualizado com sucesso: {}", agendamentoAtualizado);
        
        kafkaTemplate.send("agendamento-atualizado", agendamentoAtualizado);
        log.info("Fila enviada para o topico agendamento-atualizado: {}", agendamentoAtualizado);
        
        return agendamentoAtualizado;
    }
    
    public Boolean delete(Integer id) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(id);
        
        if (agendamento.isPresent()) {
            agendamentoRepository.deleteById(id);
            kafkaTemplate.send("agendamento-cancelado", agendamentoMapper.toDTO(agendamento.get()));
            log.info("Fila enviada para o topico agendamento-cancelado: {}", agendamentoMapper.toDTO(agendamento.get()));
            return true;
        }
        return false;
    }
    
    
    public Page<AgendamentoResponseDTO> findByPacienteId(Integer id, Pageable pageable) {
        return agendamentoRepository.findByPacienteId(id, pageable).map(agendamentoMapper::toDTO);
    }
}
