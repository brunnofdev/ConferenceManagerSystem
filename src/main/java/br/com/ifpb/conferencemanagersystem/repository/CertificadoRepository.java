package br.com.ifpb.conferencemanagersystem.repository;

import br.com.ifpb.conferencemanagersystem.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificadoRepository extends JpaRepository<Certificado,Long> {
}
