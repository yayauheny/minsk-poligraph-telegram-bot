package by.yayauheny.printbot.controller;

import by.yayauheny.printbot.dto.ProductCreateResponse;
import by.yayauheny.printbot.dto.ProductDto;
import by.yayauheny.printbot.entity.ProductEntity;
import by.yayauheny.printbot.mapper.ProductMapper;
import by.yayauheny.printbot.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/poligraph")
@RequiredArgsConstructor
public class ServiceController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> findAllProducts() {
        return productMapper.toDtoList(productService.findAllAvailableServices());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findServiceById(@RequestParam Long id) {
        return productMapper.toDto(productService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreateResponse createProduct(@RequestBody @Valid ProductDto dto) {
        ProductEntity product = productService.createProduct(productMapper.toEntity(dto));
        return productMapper.toCreateResponse(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@RequestParam Long id) {
        productService.deleteProductById(id);
    }
}
