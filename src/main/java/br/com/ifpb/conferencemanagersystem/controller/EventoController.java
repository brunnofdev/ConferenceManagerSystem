package br.com.ifpb.conferencemanagersystem.controller;

import br.com.ifpb.conferencemanagersystem.model.Evento;
import br.com.ifpb.conferencemanagersystem.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", service.findAll());
        return "event/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("event", new Evento());
        return "event/form";
    }

    @PostMapping("/save")
    public String saveEvent(@ModelAttribute Evento event) {
        service.save(event);
        return "redirect:/events";
    }

    // Ver um evento específico e suas atividades
    @GetMapping("/{id}")
    public String detailEvent(@PathVariable Long id, Model model) {
        Evento event = service.findById(id);
        model.addAttribute("event", event);
        return "event/detail"; // Página de detalhes
    }
}
