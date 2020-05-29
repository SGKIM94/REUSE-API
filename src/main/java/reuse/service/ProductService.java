package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Product;
import reuse.domain.ProductImages;
import reuse.dto.product.*;
import reuse.repository.ProductImagesRepository;
import reuse.repository.ProductRepository;
import reuse.storage.S3Uploader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static final String S3_PRODUCT_IMAGES_DIRECTORY_NAME = "products/";
    public static final String THUMBNAIL_DIRECTORY = "/thumbnail";

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
        String imageUrl = storeThumbnailImage(product, S3_PRODUCT_IMAGES_DIRECTORY_NAME);
        List<String> imagesUrl = storeProductImages(product, S3_PRODUCT_IMAGES_DIRECTORY_NAME);

        ProductImages savedProductImages = productImagesRepository.save(ProductImages.toEntity(imagesUrl));
        Product savedProduct = productRepository.save(product.toEntity(product, imageUrl, imagesUrl));

        return CreateProductResponseView.toDto(savedProduct, savedProductImages);
    }

    public ListProductResponseView list() {
        List<Product> products = productRepository.findAll();
        List<FindProductResponseView> productResponseViews = products.stream()
                .map(this::toFindProductResponseViewWithFiles)
                .collect(Collectors.toList());

        return ListProductResponseView.toDto(productResponseViews);
    }

    FindProductResponseView toFindProductResponseViewWithFiles(Product product) {
        return new FindProductResponseView(product);
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public FindProductResponseView findByIdWithImages(long id) {
        return FindProductResponseView.toDto(findById(id));
    }

    String storeThumbnailImage(CreateProductRequestView product, String directory) {
        MultipartFile productImage = product.getThumbnailImage();
        return storeProductImage(productImage, directory + getNowDateTime() + THUMBNAIL_DIRECTORY);
    }

    public List<String> storeProductImages(CreateProductRequestView product, String directory) {
        ProductImagesView productImages = ProductImagesView.toDtoByCreate(product);
        if (productImages == null) {
            return new ArrayList<>();
        }

        return productImages.convertToList().stream()
                .map(image -> storeProductImage(image, directory + getNowDateTime()))
                .collect(Collectors.toList());
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
