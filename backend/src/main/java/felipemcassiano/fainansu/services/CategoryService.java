package felipemcassiano.fainansu.services;

import felipemcassiano.fainansu.dtos.CategorySummaryDTO;
import felipemcassiano.fainansu.dtos.CategoryEntryDTO;
import felipemcassiano.fainansu.dtos.EntryDTO;
import felipemcassiano.fainansu.dtos.RegisterCategoryDTO;
import felipemcassiano.fainansu.global.exceptions.NotFoundException;
import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.Entry;
import felipemcassiano.fainansu.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CategoryEntryDTO> findAllCategoriesWithEntries(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryEntryDTO> categoryEntryDTOs = new ArrayList<>(categories.size());
        for (Category category : categories) {
            List<Entry> entries = category.getEntries();
            List<EntryDTO> entryDTOs = new ArrayList<>(entries.size());
            Long total = 0L;
            for (Entry entry : entries) {
                total = entry.getAmount();
                entryDTOs.add(EntryDTO.fromEntity(entry, converterService.amountToDouble(entry.getAmount())));
            }
            categoryEntryDTOs.add(new CategoryEntryDTO(category.getId(), category.getName(), converterService.amountToDouble(total), entryDTOs));
        }

        return categoryEntryDTOs;
    }

    public CategoryEntryDTO findCategoryWithEntries(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category with id " + categoryId + " not found"));

        List<Entry> entries = category.getEntries();
        List<EntryDTO> entryDTOS = new ArrayList<>(entries.size());

        Long total = 0L;
        for (Entry entry : entries) {
            total += entry.getAmount();
            entryDTOS.add(EntryDTO.fromEntity(entry, converterService.amountToDouble(entry.getAmount())));
        }

        return new CategoryEntryDTO(category.getId(), category.getName(), converterService.amountToDouble(total), entryDTOS);
    }

    public List<CategorySummaryDTO> findAllSummary() {
        List<Category> list = categoryRepository.findAll();

        return list.parallelStream().map(c -> {
            Long total = c.getEntries().stream().mapToLong(Entry::getAmount).sum();
            return  CategorySummaryDTO.fromEntity(c, converterService.amountToDouble(total));
        }).toList();
    }
    public void registerCategory(RegisterCategoryDTO dto) {
        Category category = new Category(dto.type(), dto.name());

        categoryRepository.save(category);
    }

}
