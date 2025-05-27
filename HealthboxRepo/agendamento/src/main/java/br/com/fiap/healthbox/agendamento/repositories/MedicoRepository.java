package br.com.fiap.healthbox.agendamento.repositories;

import br.com.fiap.healthbox.agendamento.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
