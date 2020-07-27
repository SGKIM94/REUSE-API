package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.domain.ProductImages;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ProductImagesRepository;
import reuse.repository.ProductRepository;
import reuse.storage.S3Uploader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static final String S3_PRODUCT_IMAGES_DIRECTORY_NAME = "products/";

    private final ProductRepository productRepository;
    private final ProductImagesRepository productImagesRepository;
    private final S3Uploader s3Uploader;

    public ProductService(ProductRepository productRepository, ProductImagesRepository productImagesRepository, S3Uploader s3Uploader) {
        this.productRepository = productRepository;
        this.productImagesRepository = productImagesRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public CreateProductResponseView create(CreateProductRequestView product) {
        String imageDirectory = getImageDirectory();

        ProductImages productImages = storeProductImages(product, imageDirectory);

        Product savedProduct = productRepository.save(product.toEntity(product, productImages));

        return CreateProductResponseView.toDto(savedProduct);
    }

    public ListProductResponseView list() {
        List<Product> products = productRepository.findAll();
        return ListProductResponseView.toDtoByProducts(products);
    }

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 품목이 존재하지 않습니다."));
    }

    public FindProductResponseView findByIdWithImages(long id) {
        return FindProductResponseView.toDto(findById(id));
    }

    public ProductImages storeProductImages(CreateProductRequestView product, String directory) {
        List<MultipartFile> images = product.getImages();

        if (images == null) {
            return new ProductImages();
        }

        List<String> imageUrls = storeProductImageByProductImagesView(images, directory);

        return productImagesRepository.save(ProductImages.toEntity(imageUrls));
    }

    public List<String> storeProductImageByProductImagesView(List<MultipartFile> productImages, String directory) {
        return productImages.stream()
                .map(image -> storeProductImage(image, directory))
                .collect(Collectors.toList());
    }

    // TODO : need to write test code
    private String getImageDirectory() {
        return S3_PRODUCT_IMAGES_DIRECTORY_NAME + getNowDateTime();
    }

    private LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    String storeProductImage(MultipartFile productImage, String directory) {
        if (productImage == null) {
            return "";
        }

        return s3Uploader.upload(productImage, directory);
    }
}
