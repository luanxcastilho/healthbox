package br.com.fiap.healthbox.notificacao.consumers;

import br.com.fiap.healthbox.dtos.AgendamentoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {
    
    private final Logger log = LoggerFactory.getLogger(NotificacaoConsumer.class);
    
    @KafkaListener(topics = "agendamento-criado")
    public void consumerAgendamentoCriado(AgendamentoResponseDTO agendamentoResponseDTO, Acknowledgment ack) {
        try {
            log.info("Caro "+agendamentoResponseDTO.getPaciente().getNome()+", sua consulta foi marcada para "+agendamentoResponseDTO.getDataAgendamento()+" "+agendamentoResponseDTO.getHoraAgendamento()+".");
            
            ack.acknowledge();
            log.info("Agendamento removido do topico agendamento-criado: {}", agendamentoResponseDTO);
            
        } catch (Exception e) {
            log.error("Erro ao consumir agendamento criado: {}", e.getMessage());
        }
    }
    
    @KafkaListener(topics = "agendamento-atualizado")
    public void consumerAgendamentoAtualizado(AgendamentoResponseDTO agendamentoResponseDTO, Acknowledgment ack) {
        try {
            log.info("Caro "+agendamentoResponseDTO.getPaciente().getNome()+", sua consulta foi remarcada para "+agendamentoResponseDTO.getDataAgendamento()+" "+agendamentoResponseDTO.getHoraAgendamento()+".");
            
            ack.acknowledge();
            log.info("Agendamento removido do topico agendamento-atualizado: {}", agendamentoResponseDTO);
            
        } catch (Exception e) {
            log.error("Erro ao consumir agendamento atualizado: {}", e.getMessage());
        }
    }
    
    @KafkaListener(topics = "agendamento-cancelado")
    public void consumerAgendamentoCancelado(AgendamentoResponseDTO agendamentoResponseDTO, Acknowledgment ack) {
        try {
            log.info("Caro "+agendamentoResponseDTO.getPaciente().getNome()+", seu agendamento de consulta foi cancelado.");
            
            ack.acknowledge();
            log.info("Agendamento removido do topico agendamento-cancelado: {}", agendamentoResponseDTO);
            
        } catch (Exception e) {
            log.error("Erro ao consumir agendamento cancelado: {}", e.getMessage());
        }
    }
    
}
