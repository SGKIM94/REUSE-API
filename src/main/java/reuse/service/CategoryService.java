package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.Category;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.repository.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CreateCategoryResponseView create(CreateCategoryRequestView category) {
        Category savedCategory = categoryRepository.save(category.toEntity());
        return CreateCategoryResponseView.toDto(savedCategory);
    }
}
