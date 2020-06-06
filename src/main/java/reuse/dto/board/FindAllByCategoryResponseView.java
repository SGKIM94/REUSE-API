package reuse.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reuse.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FindAllByCategoryResponseView {
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
    public FindAllByCategoryResponseView(Long id, String content, String title, LocalDateTime createAt,
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
}
