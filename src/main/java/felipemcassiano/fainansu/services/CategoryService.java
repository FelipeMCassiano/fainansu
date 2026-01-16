package felipemcassiano.fainansu.services;

import felipemcassiano.fainansu.dtos.CategoryDTO;
import felipemcassiano.fainansu.dtos.CategoryEntryDTO;
import felipemcassiano.fainansu.dtos.EntryDTO;
import felipemcassiano.fainansu.dtos.RegisterCategoryDTO;
import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.Entry;
import felipemcassiano.fainansu.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EntryService entryService;
    private final ConverterService converterService;

    public CategoryService(CategoryRepository categoryRepository, EntryService entryService, ConverterService converterService) {
        this.categoryRepository = categoryRepository;
        this.entryService = entryService;
        this.converterService = converterService;
    }


    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public CategoryEntryDTO findCategoryWithEntries(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException());

        List<Entry> entries = category.getEntries();

        Double total = converterService.amountToDouble(entries.stream().mapToLong(Entry::getAmount).sum());
        return new CategoryEntryDTO(category.getId(), category.getName(), total,entries.stream().map(e -> EntryDTO.fromEntity(e, converterService.amountToDouble(e.getAmount()))).toList());
    }

    public List<CategoryDTO> findAll() {
        List<Category> list = categoryRepository.findAll();
        return  list.stream().map(CategoryDTO::fromEntity).toList();
    }
    public void registerCategory(RegisterCategoryDTO dto) {
        Category category = new Category(dto.type(), dto.name());

        categoryRepository.save(category);
    }

}
