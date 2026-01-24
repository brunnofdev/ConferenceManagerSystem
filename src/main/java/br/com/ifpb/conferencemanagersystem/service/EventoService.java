package br.com.ifpb.conferencemanagersystem.service;

import br.com.ifpb.conferencemanagersystem.model.Atividade;
import br.com.ifpb.conferencemanagersystem.model.Evento;
import br.com.ifpb.conferencemanagersystem.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository repository;

    public EventoService(EventoRepository repository) {
        this.repository = repository;
    }

    public List<Evento> findAll() {
        return repository.findAll();
    }

    public Evento save(Evento event) {
        // Validação de Datas
        if (event.getEndDate() != null && event.getStartDate().isAfter(event.getEndDate())) {
            throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
        }
        return repository.save(event);
    }

    public Evento findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado!"));
    }

    public void addActivity(Long eventId, Atividade activity) {
        Evento event = findById(eventId);

        // Vincula a atividade ao evento
        activity.setEvent(event);

        // Adiciona na lista e o CascadeType.ALL do JPA salva a atividade automaticamente
        event.getSchedule().add(activity);

        repository.save(event); // Atualiza o evento e salva a nova atividade
    }
}