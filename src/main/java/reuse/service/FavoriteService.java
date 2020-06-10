package reuse.service;

import org.springframework.stereotype.Service;
import reuse.repository.FavoriteRepository;

@Service
public class FavoriteService {
    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }
}
