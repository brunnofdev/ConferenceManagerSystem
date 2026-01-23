package br.com.ifpb.conferencemanagersystem.service;

import br.com.ifpb.conferencemanagersystem.model.Atividade;
import br.com.ifpb.conferencemanagersystem.model.Participante;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class CertificadoService {

    public ByteArrayInputStream gerarCertificadoPdf(Participante participante, Atividade atividade) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Adiciona Logotipo, Fontes, Bordas (Lógica visual aqui)
            Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph titulo = new Paragraph("CERTIFICADO DE PARTICIPAÇÃO", fonteTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            document.add(new Paragraph(" ")); // Pular linha

            String texto = "Certificamos que " + participante.getNome() +
                    " participou da atividade " + atividade.getTema() +
                    " com carga horária de " + atividade.getCargaHoraria() + " horas.";

            Paragraph corpo = new Paragraph(texto);
            corpo.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(corpo);

            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}