package br.com.ifpb.conferencemanagersystem.service;

import br.com.ifpb.conferencemanagersystem.model.Participante;
import br.com.ifpb.conferencemanagersystem.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipanteService {

    private final ParticipanteRepository repository;

    public ParticipanteService(ParticipanteRepository repository) {
        this.repository = repository;
    }

    public List<Participante> findAll() {
        return repository.findAll();
    }

    public Participante findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante não encontrado!"));
    }

    public Participante save(Participante participant) {
        // Verifica duplicidade de e-mail antes de salvar
        // Se for uma atualização (já tem ID), verificamos se o email pertence a outro user
        if (participant.getId() == null && repository.existsByEmail(participant.getEmail())) {
            throw new IllegalArgumentException("Já existe um participante com este e-mail: " + participant.getEmail());
        }
        return repository.save(participant);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
