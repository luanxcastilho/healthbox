package br.com.fiap.healthbox.agendamento.repositories;

import br.com.fiap.healthbox.agendamento.entities.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Integer> {
    boolean existsByCoren(String coren);
}
