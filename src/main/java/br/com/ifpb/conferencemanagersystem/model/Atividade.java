package br.com.ifpb.conferencemanagersystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Atividade {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private Date data;
    private Long cargaHoraria;
    private String tema;
}
