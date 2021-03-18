package puc.tcc.compliance.api.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import puc.tcc.compliance.api.exception.ComplianceApiException;
import puc.tcc.compliance.api.mapper.DocumentMapper;
import puc.tcc.compliance.api.persistence.domain.DocumentEntity;
import puc.tcc.compliance.api.persistence.repositories.DocumentRepository;
import puc.tcc.compliance.api.resources.document.DocumentRequest;
import puc.tcc.compliance.api.resources.document.DocumentResponse;
import puc.tcc.compliance.api.services.DocumentService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    @Transactional
    public DocumentResponse saveOrUpdate(final DocumentRequest documentRequest) {
        var model = documentMapper.toModel(documentRequest);
        var document = documentRepository.findFirstByNumberOrderByVersionDesc(model.getNumber());
        int version = 1;
        if(document.isPresent()){
            version = document.get().getVersion() + 1;
            document.get().setCurrent(false);
            documentRepository.save(document.get());
        }
        model.setVersion(version);
        model.setCurrent(true);
        model.setDatUpdated(LocalDateTime.now());
        model = documentRepository.save(model);
        log.info("document saved/updated document={}", model);
        return documentMapper.toResponse(model);
    }

    @Override
    public Optional<DocumentResponse> findById(final Long id){
        var document = documentRepository.findById(id);
        return document.map(documentMapper::toResponse);
    }

    @Override
    public List<DocumentResponse> findAll(String number) {
        List<DocumentResponse> items = new ArrayList<>();
        List<DocumentEntity> documents;
        if (number == null){
            documents = documentRepository.findByCurrentTrue();
        }else{
            documents = documentRepository.findByNumberOrderByVersionDesc(number);
        }
        documents.forEach(item -> items.add(documentMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws ComplianceApiException {
        var document = documentRepository.findById(id);
        if(document.isEmpty()){
            throw new ComplianceApiException(HttpStatus.NOT_FOUND, "document", "Documento não existe!");
        }
        documentRepository.deleteById(id);
    }
}
