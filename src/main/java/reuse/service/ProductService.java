package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.storage.FileSystemStorageService;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final FileSystemStorageService fileSystemStorageService;

    public ProductService(ProductRepository productRepository, FileSystemStorageService fileSystemStorageService) {
        this.productRepository = productRepository;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @Transactional
    public CreateProductResponseView create(CreateProductRequestView product) {
        fileSystemStorageService.stores(product.getProductImages());

        Product savedProduct = productRepository.save(product.toEntity(product));
        if (savedProduct == null) {
            throw new IllegalArgumentException("Product create fail!");
        }

        return CreateProductResponseView.toDto(savedProduct);
    }

    public ListProductResponseView list() {
        return ListProductResponseView.toDto(productRepository.findAll());
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
