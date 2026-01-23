package br.com.ifpb.conferencemanagersystem.service;

import br.com.ifpb.conferencemanagersystem.model.Participante;
import br.com.ifpb.conferencemanagersystem.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    public Participante salvarParticipante(Participante participante) {
        if (participanteRepository.existsByEmail(participante.getEmail())) {
            throw new IllegalArgumentException("Já existe um participante com este e-mail!");
        }

        return participanteRepository.save(participante);
    }
}
