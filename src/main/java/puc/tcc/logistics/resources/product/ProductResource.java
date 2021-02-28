package puc.tcc.logistics.resources.product;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import puc.tcc.logistics.services.ProductService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> create(@RequestBody final ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrUpdate(productRequest));
    }

    @PutMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> update(@RequestBody final ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveOrUpdate(productRequest));
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Long id){
        var response = productService.findById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductResponse>> findAll(){
        var response = productService.findAll();
        if(response.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product/{id}/upload")
    public ResponseEntity<Void> uploadFile(@PathVariable("id") Long id,
                                        @RequestParam("file") MultipartFile uploadfile) throws IOException, NotFoundException {
        productService.upload(id, uploadfile.getBytes(), uploadfile.getOriginalFilename());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws NotFoundException, MalformedURLException {
        Resource resource = productService.download(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
