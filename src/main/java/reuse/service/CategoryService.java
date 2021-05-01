package reuse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.Category;
import reuse.dto.category.CreateCategoryRequestView;
import reuse.dto.category.CreateCategoryResponseView;
import reuse.dto.category.FindCategoryResponseView;
import reuse.repository.CategoryRepository;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CreateCategoryResponseView create(CreateCategoryRequestView category) {
        Category savedCategory = categoryRepository.save(category.toEntity());
        return CreateCategoryResponseView.toDto(savedCategory);
    }

    @Transactional(readOnly = true)
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public FindCategoryResponseView retrieve(long id) {
        return FindCategoryResponseView.toDto(findById(id));
    }
}
