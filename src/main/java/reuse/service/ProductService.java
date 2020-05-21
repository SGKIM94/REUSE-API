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
    public static final String S3_PRODUCT_IMAGES_DIRECTORY_NAME = "products/";
    private final ProductRepository productRepository;
    private final S3Uploader s3Uploader;

    public ProductService(ProductRepository productRepository, S3Uploader s3Uploader) {
        this.productRepository = productRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public CreateProductResponseView create(CreateProductRequestView product) {
        String imageUrl = storeProductImages(product, S3_PRODUCT_IMAGES_DIRECTORY_NAME);
        Product savedProduct = productRepository.save(product.toEntity(product));
        return CreateProductResponseView.toDto(savedProduct, imageUrl);
    }

    String storeProductImages(CreateProductRequestView product, String directory) {
        return s3Uploader.upload(product.getProductImage(), directory + product.getId());
    }

    public ListProductResponseView list() {
        List<Product> products = productRepository.findAll();
        List<FindProductResponseView> productResponseViews = products.stream()
                .map(this::toFindProductResponseViewWithFiles)
                .collect(Collectors.toList());

        return ListProductResponseView.toDto(productResponseViews);
    }

    private FindProductResponseView toFindProductResponseViewWithFiles(Product product) {
        return new FindProductResponseView(product, s3Uploader.getFiles(product.getId()));
    }

    private String getFileNameByString(Path path) {
        return path.getFileName().toString();
    }

    public FindProductResponseView findById(long id) {
        Product product = productRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return FindProductResponseView.toDto(product);
    }
}
