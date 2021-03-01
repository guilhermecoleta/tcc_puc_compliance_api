package puc.tcc.logistics.resources.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import puc.tcc.logistics.services.SupplierService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
public class SupplierResource {
    private static String UPLOADED_FOLDER = "F://temp//";

    @Autowired
    private SupplierService supplierService;

    @PostMapping(value = "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierResponse> create(@RequestBody final SupplierRequest supplierRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.saveOrUpdate(supplierRequest));
    }

    @PutMapping(value = "/suppliers", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SupplierResponse> update(@RequestBody final SupplierRequest supplierRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.saveOrUpdate(supplierRequest));
    }

    @GetMapping(value = "/suppliers/{id}")
    public ResponseEntity<SupplierResponse> findById(@PathVariable("id") Long id){
        var response = supplierService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/suppliers")
    public ResponseEntity<List<SupplierResponse>> findAll(){
        var response = supplierService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/suppliers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        supplierService.delete(id);
        return ResponseEntity.ok().build();
    }

}
