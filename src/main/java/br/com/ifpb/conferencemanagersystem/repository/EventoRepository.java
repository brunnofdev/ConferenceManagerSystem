package br.com.ifpb.conferencemanagersystem.repository;

import br.com.ifpb.conferencemanagersystem.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long>{
}
