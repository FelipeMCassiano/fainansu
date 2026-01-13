package felipemcassiano.fainansu.dtos;

import felipemcassiano.fainansu.models.CategoryType;

public record RegisterCategoryDTO(String name, CategoryType type) {
}
