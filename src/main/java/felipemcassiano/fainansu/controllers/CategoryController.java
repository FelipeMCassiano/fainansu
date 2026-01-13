package felipemcassiano.fainansu.controllers;

import felipemcassiano.fainansu.dtos.CategoryDTO;
import felipemcassiano.fainansu.dtos.CategoryEntryDTO;
import felipemcassiano.fainansu.dtos.RegisterCategoryDTO;
import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.repositories.CategoryRepository;
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


    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> response = categoryService.findAll();
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Void> post(@RequestBody RegisterCategoryDTO categoryDTO) {
        categoryService.registerCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}/entries")
    public ResponseEntity<CategoryEntryDTO> getCategoryWithEntries(@PathVariable String id) {
        CategoryEntryDTO response = categoryService.findCategoryWithEntries(id);

        return ResponseEntity.ok(response);
    }
}
