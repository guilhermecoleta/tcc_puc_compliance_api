package puc.tcc.logistics.resources.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import puc.tcc.logistics.services.SupplyService;

import java.util.List;

@RestController
public class SupplyResource {

    @Autowired
    private SupplyService supplyService;

    @PostMapping(value = "/supplies", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyResponse> create(@RequestBody final SupplyRequest supplyRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.saveOrUpdate(supplyRequest));
    }

    @PutMapping(value = "/supplies", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplyResponse> update(@RequestBody final SupplyRequest supplyRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplyService.saveOrUpdate(supplyRequest));
    }

    @GetMapping(value = "/supplies/{id}")
    public ResponseEntity<SupplyResponse> findById(@PathVariable("id") Long id){
        var response = supplyService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/supplies")
    public ResponseEntity<List<SupplyResponse>> findAll(){
        var response = supplyService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/supplies/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        supplyService.delete(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
//                                         @RequestParam("userId") Integer UserId,
//                                         @RequestParam("docType") String docType) {
//        String fileName = documneStorageService.storeFile(file, UserId, docType);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }
}
