package by.kirylarol.spendsculptor.repos;


import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Scope;

import java.util.List;

import java.util.ArrayList;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "category_id")
    private int categoryId;

    @Column
    private String categoryName;

    @OneToMany (mappedBy = "category",fetch=FetchType.EAGER)
    private List<Position> positions = new ArrayList<>();

    public int categoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String categoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
