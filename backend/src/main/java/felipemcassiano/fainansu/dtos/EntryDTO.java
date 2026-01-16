package felipemcassiano.fainansu.dtos;

import felipemcassiano.fainansu.models.Entry;
import felipemcassiano.fainansu.models.EntryType;

import java.time.LocalDateTime;

public record EntryDTO(String categoryId, String description, Double amount, EntryType type, LocalDateTime date) {

    public static  EntryDTO fromEntity(Entry entry, Double amount) {
        return new EntryDTO(entry.getCategoryId(), entry.getDescription(), amount,  entry.getType(),  entry.getDate());
    }
}
