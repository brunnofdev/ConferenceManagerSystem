package br.com.ifpb.conferencemanagersystem.repository;

import br.com.ifpb.conferencemanagersystem.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
}
