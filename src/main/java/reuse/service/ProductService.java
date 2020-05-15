package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Product;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductRepository;
import reuse.storage.S3Uploader;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final S3Uploader s3Uploader;

    public ProductService(ProductRepository productRepository, S3Uploader s3Uploader) {
        this.productRepository = productRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public CreateProductResponseView create(CreateProductRequestView product) {
        storeProductImages(product);
        Product savedProduct = productRepository.save(product.toEntity(product));
        return CreateProductResponseView.toDto(savedProduct);
    }

    void storeProductImages(CreateProductRequestView product) {
        s3Uploader.upload(product.getProductImage(), "products/" + product.getId());
    }

    public ListProductResponseView list() {
        List<Product> products = productRepository.findAll();
        List<FindProductResponseView> productResponseViews = products.stream()
                .map(this::toFindProductResponseViewWithFiles)
                .collect(Collectors.toList());

        return ListProductResponseView.toDto(productResponseViews);
    }

    private FindProductResponseView toFindProductResponseViewWithFiles(Product product) {
        return new FindProductResponseView();
    }

    private String getFileNameByString(Path path) {
        return path.getFileName().toString();
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
