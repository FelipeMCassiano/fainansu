package felipemcassiano.fainansu.dtos;

import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.CategoryType;

public record CategoryDTO(String id, String name, CategoryType type) {
    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getType());
    }
}
