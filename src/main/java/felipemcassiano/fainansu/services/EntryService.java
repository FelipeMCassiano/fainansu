package felipemcassiano.fainansu.services;

import felipemcassiano.fainansu.dtos.EntryDTO;
import felipemcassiano.fainansu.dtos.RegisterEntryDTO;
import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.Entry;
import felipemcassiano.fainansu.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryService {

    private final CategoryRepository categoryRepository;

    public EntryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<EntryDTO> findAllEntries(boolean shouldConvert) {
        List<Entry> list = categoryRepository.findAllEntries();

        return  list.stream().map(e -> EntryDTO.fromEntity(e, shouldConvert? convertAmountToDouble(e.getAmount()): e.getAmount())).toList();
    }

    public void registerEntry(RegisterEntryDTO dto) {

        Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(()-> new RuntimeException());


        Entry newEntry = new Entry(dto.description(), convertAmountToLong(dto.amount()), LocalDateTime.now(),dto.type(), dto.categoryId());

        category.addEntry(newEntry);
        categoryRepository.save(category);
    }


    public Long convertAmountToLong(Double value) {
        Double  d = (value * 100 );
        return d.longValue();
    }

    public Double convertAmountToDouble(Long value) {
        Double l = (value.doubleValue() / 100.0);
        return l;
    }

}
