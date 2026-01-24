package br.com.ifpb.conferencemanagersystem.controller;

import br.com.ifpb.conferencemanagersystem.service.CertificadoService;
import br.com.ifpb.conferencemanagersystem.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certificates")
@RequiredArgsConstructor
public class CertificadoController {

    private final EventoService eventService;
    private final CertificadoService certificadoService;

    // Tela onde o usuário escolhe o evento para emitir certificado
    @GetMapping("/issue")
    public String selectEventPage(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "certificate/select-event";
    }

    // Gera o PDF
    @GetMapping("/download/{eventId}/{participantId}")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long eventId, @PathVariable Long participantId) {
        byte[] pdfBytes = certificadoService.generateCertificatePdf(eventId, participantId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificado.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
