package felipemcassiano.fainansu.models;

import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

public class Entry {
    private String description;
    private String categoryId;

    private Long amount;

    @Indexed
    private LocalDateTime date;
    private EntryType entryType;

    public Entry(String description, Long amount, LocalDateTime date, EntryType entryType, String categoryId) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.entryType = entryType;
        this.categoryId = categoryId;
    }

    public Entry() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EntryType getType() {
        return entryType;
    }

    public void setType(EntryType entryType) {
        this.entryType = entryType;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
