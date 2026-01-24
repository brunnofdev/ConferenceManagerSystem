package br.com.ifpb.conferencemanagersystem.controller;

import br.com.ifpb.conferencemanagersystem.model.Participante;
import br.com.ifpb.conferencemanagersystem.service.ParticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/participants")
@RequiredArgsConstructor // Injeção de dependência automática do Service
public class ParticipanteController {

    private final ParticipanteService service;

    @GetMapping
    public String listParticipants(Model model) {
        model.addAttribute("participants", service.findAll());
        return "participant/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("participant", new Participante());
        return "participant/form";
    }

    @PostMapping
    public String saveParticipant(@ModelAttribute Participante participant, RedirectAttributes redirectAttributes) {
        try {
            service.save(participant);
            redirectAttributes.addFlashAttribute("message", "Participante cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            // Se der erro (ex: email duplicado), volta pro form com o erro
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/participants/new";
        }
        return "redirect:/participants"; // Padrão PRG: Redireciona para a lista
    }

    // Remove e recarrega a página
    @GetMapping("/delete/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/participants";
    }
}