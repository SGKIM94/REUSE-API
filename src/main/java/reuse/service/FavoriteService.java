package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.User;
import reuse.dto.favorite.CreateFavoriteRequestView;
import reuse.dto.favorite.CreateFavoriteResponseView;
import reuse.repository.FavoriteRepository;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional
    public CreateFavoriteResponseView save(User loginUser, CreateFavoriteRequestView favorite) {
        return CreateFavoriteResponseView.toDtoEntity(favoriteRepository.save(favorite.toEntity(loginUser)));
    }
}
