package reuse.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.storage.FileSystemStorageService;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Product> products = productRepository.findAll();
        loadAllProductImagesInProductId();
        products.stream()
                .flatMap(product -> loadAllProductImagesInProductId(product.getId()).stream()
                .map(resource -> new FindProductResponseView(resource))
                .map(ListProductResponseView.toDto(products, ))
    }

    List<Resource> loadAllProductImagesInProductId(Long productId) {
        fileSystemStorageService.assignRootLocationToProductId(productId.toString());
        return fileSystemStorageService.loadAll()
                .map(path -> fileSystemStorageService.loadAsResource(path.getFileName().toString()))
                .collect(Collectors.toList());
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
