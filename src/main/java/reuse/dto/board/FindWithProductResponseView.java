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
    private String thumbnailImage;
    private Boolean isSold;
    private Boolean isUsed;
    private String price;
    private String name;
    private String quality;

    @QueryProjection
    public FindWithProductResponseView(Long id, String content, String title, LocalDateTime createAt,
                                       LocalDateTime updateAt, User seller, String sellerAddress, String thumbnailImage,
                                       Boolean isSold, Boolean isUsed, String price, String name, String quality) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.seller = seller;
        this.sellerAddress = sellerAddress;
        this.thumbnailImage = thumbnailImage;
        this.isSold = isSold;
        this.isUsed = isUsed;
        this.price = price;
        this.name = name;
        this.quality = quality;
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
        this.thumbnailImage = product.getThumbnailImage();
        this.isSold = product.getIsSold();
        this.isUsed = product.getIsUsed();
        this.price = product.getPrice();
        this.name = product.getName();
        this.quality = product.getQuality();
    }
}
