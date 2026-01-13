package felipemcassiano.fainansu.repositories;

import felipemcassiano.fainansu.models.Category;
import felipemcassiano.fainansu.models.Entry;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Aggregation(pipeline = {
            "{unwind:  '$entires'}",
            "{$replaceRoot:  {newRoot: '$entries'}}"
    })
    List<Entry> findAllEntries();
}
