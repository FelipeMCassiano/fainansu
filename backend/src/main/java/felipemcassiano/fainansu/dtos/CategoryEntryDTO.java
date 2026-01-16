package felipemcassiano.fainansu.dtos;

import java.util.List;

public record CategoryEntryDTO(String categoryId, String name, Double total
        ,List<EntryDTO> entries ) {
}
