package br.com.ifpb.conferencemanagersystem.repository;

import br.com.ifpb.conferencemanagersystem.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    boolean existsByEmail(String email);
}
