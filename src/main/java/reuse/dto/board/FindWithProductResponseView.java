package reuse.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.Board;
import reuse.domain.Product;
import reuse.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FindWithProductResponseView {
    private Long id;
    private String content;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User seller;
    private String sellerAddress;
    private Boolean isSold;
    private Boolean isUsed;
    private String price;
    private String name;
    private String quality;
    private String mainImage;

    @QueryProjection
    public FindWithProductResponseView(Long id, String content, String title, LocalDateTime createAt,
                                       LocalDateTime updateAt, User seller, String sellerAddress, Boolean isSold,
                                       Boolean isUsed, String price, String name, String quality, String url) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.isSold = isSold;
        this.isUsed = isUsed;
        this.price = price;
        this.name = name;
        this.quality = quality;
        this.mainImage = url;
    }

    @Builder
    public FindWithProductResponseView(Board board) {
        Product product = board.getProduct();

        this.id = board.getId();
        this.content = board.getContent();
        this.title = board.getTitle();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
        this.seller = board.getSeller();
        this.sellerAddress = board.getSellerAddress();
        this.isSold = product.getIsSold();
        this.isUsed = product.getIsUsed();
        this.price = product.getPrice();
        this.name = product.getName();
        this.quality = product.getQuality();
        this.mainImage = product.retrieveMainImage();
    }
}
