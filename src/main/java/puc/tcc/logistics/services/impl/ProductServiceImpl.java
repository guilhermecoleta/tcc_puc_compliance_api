package puc.tcc.logistics.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import puc.tcc.logistics.exception.LogisticsException;
import puc.tcc.logistics.mapper.ProductMapper;
import puc.tcc.logistics.persistence.domain.ProductEntity;
import puc.tcc.logistics.persistence.domain.SupplierEntity;
import puc.tcc.logistics.persistence.repositories.ProductRepository;
import puc.tcc.logistics.persistence.repositories.SupplierRepository;
import puc.tcc.logistics.resources.product.ProductRequest;
import puc.tcc.logistics.resources.product.ProductResponse;
import puc.tcc.logistics.services.ProductService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private static final String PATH = "files//";
    private static final String PRODUCT = "product";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Page<ProductEntity> findAll(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return productRepository.search(pageRequest);
    }

    @Override
    public Optional<ProductResponse> findById(final Long id){
        var product = productRepository.findById(id);
        return product.map(productMapper::toResponse);
    }

    @Override
    public List<ProductResponse> findAll() {
        List<ProductResponse> items = new ArrayList<>();
        var products = productRepository.findAll();
        products.forEach(item -> items.add(productMapper.toResponse(item)));
        return items;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws LogisticsException {
        var product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new LogisticsException(HttpStatus.NOT_FOUND, PRODUCT, "Produto não existe");
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductResponse saveOrUpdate(final ProductRequest productRequest) throws LogisticsException {
        var model = productMapper.toModel(productRequest);
        Optional<SupplierEntity> supplier = supplierRepository.findById(productRequest.getSupplier());
        if(supplier.isEmpty()){
            throw new LogisticsException(HttpStatus.BAD_REQUEST, "supplier", "Fornecedor não existe");
        }
        model = productRepository.save(model);
        log.info("Product saved/updated id={}", model.getId());
        return productMapper.toResponse(model);
    }

    @Override
    @Transactional
    public void upload(Long id, byte[] file, String filename) throws IOException, LogisticsException {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isEmpty()) throw new LogisticsException(HttpStatus.NOT_FOUND, PRODUCT, "Produto não encontrado!");
        ProductEntity entity = productEntity.get();
        String filePath = getFilePath(filename, entity.getName());
        entity.setFilePath(filePath);
        productRepository.save(entity);
        Path path = Paths.get( PATH + filePath);
        Files.write(path, file);
    }

    private String getFilePath(String filename, String name) {
        Calendar calendar = Calendar.getInstance();
        return name + "-" + calendar.getTimeInMillis() + "." + FilenameUtils.getExtension(filename);
    }

    @Override
    public Resource download(Long id) throws MalformedURLException, LogisticsException, URISyntaxException {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isEmpty()) throw new LogisticsException(HttpStatus.NOT_FOUND, PRODUCT, "Produto não encontrado!");

        Path path = Paths.get(PATH + productEntity.get().getFilePath());
        File file = path.toFile();
        if(!file.exists()){
            path = Paths.get(getDefault());
        }
        return new UrlResource(path.toUri());
    }

    private String getDefault() {
        URL resource = getClass().getClassLoader().getResource("produto-default.png");
        return resource.getPath();
    }
}
