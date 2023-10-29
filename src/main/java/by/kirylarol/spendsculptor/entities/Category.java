package by.kirylarol.spendsculptor.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

import java.util.ArrayList;

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
