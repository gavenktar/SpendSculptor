package by.kirylarol.spendsculptor.entities;


import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "category_id")
    private int categoryId;

    @Column(unique = true, name = "category_name")
    private String categoryName;

    @OneToMany (mappedBy = "category",fetch=FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private List<Position> positions = new ArrayList<>();


    @JsonGetter
    public int categoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    @JsonGetter
    public String categoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryName, category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, categoryName, positions);
    }
}
