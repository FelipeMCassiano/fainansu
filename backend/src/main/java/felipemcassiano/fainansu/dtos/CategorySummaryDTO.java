package felipemcassiano.fainansu.dtos;

import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.CategoryType;

public record CategorySummaryDTO(String id, String name, CategoryType type, Double total) {
    public static CategorySummaryDTO fromEntity(Category category, Double total) {
        return new CategorySummaryDTO(category.getId(), category.getName(), category.getType(), total);
    }
}
