package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reuse.domain.Image;
import reuse.domain.Product;
import reuse.domain.ProductImages;
import reuse.dto.product.CreateProductRequestView;
import reuse.dto.product.CreateProductResponseView;
import reuse.dto.product.FindProductResponseView;
import reuse.dto.product.ListProductResponseView;
import reuse.repository.ImageRepository;
import reuse.repository.ProductRepository;
import reuse.storage.S3Uploader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static final String S3_PRODUCT_IMAGES_DIRECTORY_NAME = "products/";

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository, S3Uploader s3Uploader) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public CreateProductResponseView create(CreateProductRequestView product) {
        String imageDirectory = getImageDirectory(getNowDateTime());

        Product savedProduct = productRepository.save(product.toEntity(product));

        storeProductImages(product, imageDirectory, savedProduct);

        return CreateProductResponseView.toDto(savedProduct);
    }

    @Transactional(readOnly = true)
    public ListProductResponseView list() {
        List<Product> products = productRepository.findAll();
        return ListProductResponseView.toDtoByProducts(products);
    }

    @Transactional(readOnly = true)
    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 품목이 존재하지 않습니다."));
    }

    public FindProductResponseView retrieve(long id) {
        return FindProductResponseView.toDto(findById(id));
    }

    @Transactional
    public ProductImages storeProductImages(CreateProductRequestView product, String directory, Product savedProduct) {
        List<MultipartFile> imageFiles = product.getImages();

        if (imageFiles == null) {
            return new ProductImages();
        }

        List<String> imageUrls = storeProductImageByProductImagesView(imageFiles, directory);

        List<Image> images = Image.convertUrlsToImages(savedProduct, imageUrls);

        imageRepository.saveAll(images);

        return ProductImages.toEntity(images);
    }

    @Transactional
    public List<String> storeProductImageByProductImagesView(List<MultipartFile> productImages, String directory) {
        return productImages.stream()
                .map(image -> storeProductImage(image, directory))
                .collect(Collectors.toList());
    }

    String getImageDirectory(LocalDateTime date) {
        return S3_PRODUCT_IMAGES_DIRECTORY_NAME + date;
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
