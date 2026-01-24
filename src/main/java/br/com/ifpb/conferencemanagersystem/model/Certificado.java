package br.com.ifpb.conferencemanagersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "certificate")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Gera ID único automaticamente (Ex: a0eebc99-9c0b...)
    private UUID validationCode;

    private LocalDateTime emissionDate;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participante participant;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evento event;

    // Construtor utilitário para facilitar criação
    public Certificado(Participante participant, Evento event) {
        this.participant = participant;
        this.event = event;
        this.emissionDate = LocalDateTime.now();
    }
}
