package by.yayauheny.printbot.service;

import by.yayauheny.printbot.dto.ProductDto;
import by.yayauheny.printbot.entity.ProductEntity;
import by.yayauheny.printbot.exception.CannotUpdateException;
import by.yayauheny.printbot.exception.NoSuchEntityException;
import by.yayauheny.printbot.mapper.ProductMapper;
import by.yayauheny.printbot.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductEntity> findAllAvailableServices() {
        return productRepository.findAllByAvailableIsTrue();
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id [%s] not found"
                        .formatted(id)));
    }

    public ProductEntity findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Product with name [%s] not found"
                        .formatted(name)));
    }

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(ProductDto updatedProduct) {
        try {
            Optional<ProductEntity> product = productRepository.findByName(updatedProduct.name());
            ProductEntity entityToUpdate = product.orElseThrow(() ->
                    new NoSuchEntityException("Product with name [%s] not found".formatted(updatedProduct.name())));

            productMapper.update(entityToUpdate, updatedProduct);

            return entityToUpdate;
        } catch (Exception e) {
            throw new CannotUpdateException("Exception caused during updating process, check data and try again");
        }
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
