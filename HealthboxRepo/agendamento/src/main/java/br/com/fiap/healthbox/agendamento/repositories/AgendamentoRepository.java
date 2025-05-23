package br.com.fiap.healthbox.agendamento.repositories;

import br.com.fiap.healthbox.agendamento.entities.Agendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Page<Agendamento> findByPacienteId(Integer id, Pageable pageable);
}
