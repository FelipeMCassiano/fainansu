package felipemcassiano.fainansu.controllers;

import felipemcassiano.fainansu.dtos.CategorySummaryDTO;
import felipemcassiano.fainansu.dtos.CategoryEntryDTO;
import felipemcassiano.fainansu.dtos.RegisterCategoryDTO;
import felipemcassiano.fainansu.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/summary")
    public ResponseEntity<List<CategorySummaryDTO>> getAllSummary() {
        List<CategorySummaryDTO> response = categoryService.findAllSummary();
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryEntryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.findAllCategoriesWithEntries());
    }

    @PostMapping("")
    public ResponseEntity<Void> post(@RequestBody RegisterCategoryDTO categoryDTO) {
        categoryService.registerCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryEntryDTO> getCategoryWithEntries(@PathVariable String id) {
        CategoryEntryDTO response = categoryService.findCategoryWithEntries(id);

        return ResponseEntity.ok(response);
    }
}
