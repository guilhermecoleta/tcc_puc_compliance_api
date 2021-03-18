package puc.tcc.compliance.api.resources.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.tcc.compliance.api.exception.ComplianceApiException;
import puc.tcc.compliance.api.services.DocumentService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DocumentResource {

    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/documents", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResponse> create(@Valid @RequestBody final DocumentRequest documentRequest) throws ComplianceApiException {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.saveOrUpdate(documentRequest));
    }

    @GetMapping(value = "/documents/{id}")
    public ResponseEntity<DocumentResponse> findById(@PathVariable("id") Long id){
        var response = documentService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/documents")
    public ResponseEntity<List<DocumentResponse>> findAll(){
        var response = documentService.findAll(null);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/documents/all/{number}")
    public ResponseEntity<List<DocumentResponse>> findAll(@PathVariable("number") String number){
        var response = documentService.findAll(number);
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/documents/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ComplianceApiException {
        documentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
