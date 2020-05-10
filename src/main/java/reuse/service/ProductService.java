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
        storeProductImages(product);

        Product savedProduct = productRepository.save(product.toEntity(product));
        if (savedProduct == null) {
            throw new IllegalArgumentException("Product create fail!");
        }

        return CreateProductResponseView.toDto(savedProduct);
    }

    void storeProductImages(CreateProductRequestView product) {
        // TODO: product Id 가지는 directory 생성 후 거기에 저장하는 로직 추가 필요
        // TODO: 해당 product Id 에 저장된 images 가져오는 로직 필요
        fileSystemStorageService.addProductIdInLocation(product.getId().toString());
        fileSystemStorageService.stores(product.getProductImages());
    }

    public ListProductResponseView list() {
        return ListProductResponseView.toDto(productRepository.findAll());
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
