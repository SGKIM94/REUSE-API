package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Favorite;
import reuse.domain.User;
import reuse.dto.favorite.FavoriteCreateRequestView;
import reuse.dto.favorite.FavoriteCreateResponseView;
import reuse.dto.favorite.FavoriteListResponseView;
import reuse.repository.FavoriteRepository;

import java.util.List;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    public FavoriteCreateResponseView save(User loginUser, FavoriteCreateRequestView favorite) {
        return FavoriteCreateResponseView.toDtoEntity(favoriteRepository.save(favorite.toEntity(loginUser)));
    }
}
