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

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

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
        storeProductImages(product);

        Product savedProduct = productRepository.save(product.toEntity(product));
        return CreateProductResponseView.toDto(savedProduct);
    }

    void storeProductImages(CreateProductRequestView product) {
        fileSystemStorageService.assignRootLocationToProductId(product.getId().toString());
        fileSystemStorageService.init();
        fileSystemStorageService.store(product.getProductImage());
//        fileSystemStorageService.stores(product.getProductImages());
    }

    public ListProductResponseView list() {
//        return ListProductResponseView.toDto(productRepository.findAll());
        List<Product> products = productRepository.findAll();
//        products.getProducts().stream()
//                .flatMap(product -> loadAllProductImagesInProductId(product.getId()))
//                .map(product -> )
    }

    Stream<Path> loadAllProductImagesInProductId(Long productId) {
        fileSystemStorageService.assignRootLocationToProductId(productId.toString());
        fileSystemStorageService.loadAll().map(path -> path.resolve());
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
