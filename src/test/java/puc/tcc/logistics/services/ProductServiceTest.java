package puc.tcc.logistics.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import puc.tcc.logistics.SampleBaseTestCase;
import puc.tcc.logistics.exception.LogisticsException;
import puc.tcc.logistics.mapper.ProductMapper;
import puc.tcc.logistics.persistence.domain.ProductEntity;
import puc.tcc.logistics.persistence.domain.SupplierEntity;
import puc.tcc.logistics.persistence.repositories.ProductRepository;
import puc.tcc.logistics.persistence.repositories.SupplierRepository;
import puc.tcc.logistics.resources.product.ProductRequest;
import puc.tcc.logistics.resources.product.ProductResponse;
import puc.tcc.logistics.services.impl.ProductServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles
class ProductServiceTest extends SampleBaseTestCase {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private final ProductService productService = new ProductServiceImpl();


    @Test
    @DisplayName("Salvar produto com sucesso")
    void save() throws LogisticsException {
        when(productMapper.toModel(any())).thenReturn(getProductEntity());
        when(supplierRepository.findById(anyLong())).thenReturn(getSupplierOptional());
        when(productRepository.save(any())).thenReturn(getProductEntity());
        when(productMapper.toResponse(any())).thenReturn(getProductResponse());
        var product = productService.saveOrUpdate(getProductRequest());
        assertProduct(getProductRequest(), product);
        ArgumentCaptor<ProductEntity> captor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(captor.capture());

        var productEntity = captor.getValue();
        assertProduct(getProductRequest(), productEntity);
    }

    @Test
    @DisplayName("Fornecedor não existe")
    void saveWithoutSupplier(){
        when(productMapper.toModel(any())).thenReturn(getProductEntityWithoutSupplier());
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(LogisticsException.class, () -> {
            productService.saveOrUpdate(getProductRequest());
        });

        String expectedMessage = "Fornecedor não existe";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    private ProductRequest getProductRequest(){
        return ProductRequest.builder()
                .description("Descrição")
                .name("None")
                .supplier(1L)
                .build();
    }

    private ProductEntity getProductEntity(){
        return getProduct()
                .supplier(getSupplier())
                .build();
    }

    private ProductEntity getProductEntityWithoutSupplier(){
        return getProduct()
                .build();
    }

    private ProductEntity.ProductEntityBuilder getProduct() {
        return ProductEntity.builder()
                .description("Descrição")
                .name("None");
    }

    private ProductResponse getProductResponse(){
        return ProductResponse.builder()
                .id(1L)
                .description("Descrição")
                .name("None")
                .supplier(1L)
                .build();
    }

    private Optional<SupplierEntity> getSupplierOptional() {
        return Optional.of(getSupplier());
    }

    private SupplierEntity getSupplier() {
        return SupplierEntity.builder().id(1L).build();
    }

    private void assertProduct(ProductRequest expected, ProductResponse actual){
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSupplier(), actual.getSupplier());
    }

    private void assertProduct(ProductRequest expected, ProductEntity actual){
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSupplier(), actual.getSupplier().getId());
    }
}
