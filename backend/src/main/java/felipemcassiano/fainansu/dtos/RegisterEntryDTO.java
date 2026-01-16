package felipemcassiano.fainansu.dtos;

import felipemcassiano.fainansu.models.EntryType;

public record RegisterEntryDTO(Double amount, EntryType type, String categoryId, String description ) {
}
