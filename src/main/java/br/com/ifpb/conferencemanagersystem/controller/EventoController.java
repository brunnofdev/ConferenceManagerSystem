package br.com.ifpb.conferencemanagersystem.controller;

import br.com.ifpb.conferencemanagersystem.model.Evento;
import br.com.ifpb.conferencemanagersystem.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String saveEvent(@ModelAttribute Evento event, RedirectAttributes redirectAttributes) {
        try {
            service.save(event);
            redirectAttributes.addFlashAttribute("success", "Evento salvo com sucesso!");
            return "redirect:/events";
        } catch (IllegalArgumentException e) {
            // Se der erro de data, volta para o formulário e mostra o erro
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/events/new";
        }
    }

    // Ver um evento específico e suas atividades
    @GetMapping("/{id}")
    public String detailEvent(@PathVariable Long id, Model model) {
        Evento event = service.findById(id);
        model.addAttribute("event", event);
        return "event/detail"; // Página de detalhes
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/events";
    }

    @PostMapping("/{id}/inscrever")
    public String inscriber(@PathVariable Long id, @RequestParam String email, RedirectAttributes redirectAttributes) {
        try {
            service.inscreverParticipante(id, email);
            redirectAttributes.addFlashAttribute("success", "Inscrição realizada!");
        } catch (IllegalArgumentException e) {
            // Captura o "Participante não encontrado" e mostra na tela
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/events/" + id;
    }
}
