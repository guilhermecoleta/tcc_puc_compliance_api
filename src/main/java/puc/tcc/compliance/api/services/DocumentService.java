package puc.tcc.compliance.api.services;


import org.springframework.data.domain.Page;
import puc.tcc.compliance.api.exception.ComplianceApiException;
import puc.tcc.compliance.api.resources.document.DocumentRequest;
import puc.tcc.compliance.api.persistence.domain.DocumentEntity;
import puc.tcc.compliance.api.resources.document.DocumentResponse;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    DocumentResponse saveOrUpdate(final DocumentRequest documentRequest) throws ComplianceApiException;

    Page<DocumentEntity> findAll(int page, int size);

    Optional<DocumentResponse> findById(final Long id);

    List<DocumentResponse> findAll();

    void delete(final Long id) throws ComplianceApiException;
}
