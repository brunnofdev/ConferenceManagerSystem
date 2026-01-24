package br.com.ifpb.conferencemanagersystem.service;

import br.com.ifpb.conferencemanagersystem.model.Certificado;
import br.com.ifpb.conferencemanagersystem.model.Evento;
import br.com.ifpb.conferencemanagersystem.model.Participante;
import br.com.ifpb.conferencemanagersystem.repository.CertificadoRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository certificateRepository;
    private final EventoService eventService;
    private final ParticipanteService participantService;

    public byte[] generateCertificatePdf(Long eventId, Long participantId) {
        // Busca dados
        Evento event = eventService.findById(eventId);
        Participante participant = participantService.findById(participantId);

        // Cria o registro do certificado no banco (Persistência)
        Certificado certificate = new Certificado(participant, event);
        certificateRepository.save(certificate);

        // Gera o PDF em memória (Lógica do OpenPDF)
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4.rotate()); // Formato Paisagem
            PdfWriter.getInstance(document, out);

            document.open();

            // --- Design do Certificado ---
            // Fonte Padrão
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 30);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 18);

            // Título
            Paragraph title = new Paragraph("CERTIFICADO DE PARTICIPAÇÃO", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(50);
            document.add(title);

            // Corpo do Texto
            String text = String.format(
                    "Certificamos que \n\n%s\n\n participou do evento \"%s\", realizado em %s.\n\n" +
                            "Carga Horária: XX horas",
                    participant.getName().toUpperCase(),
                    event.getName(),
                    event.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );

            Paragraph body = new Paragraph(text, textFont);
            body.setAlignment(Element.ALIGN_CENTER);
            body.setSpacingAfter(50);
            document.add(body);

            // Código de Validação (UUID)
            Paragraph validation = new Paragraph("Código de Validação: " + certificate.getValidationCode(),
                    FontFactory.getFont(FontFactory.COURIER, 12));
            validation.setAlignment(Element.ALIGN_CENTER);
            document.add(validation);

            document.close();

            return out.toByteArray(); // Retorna o arquivo binário

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF do certificado", e);
        }
    }
}