package com.food.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String c_name;
    private String c_description;
    private String c_image;
    @OneToMany(mappedBy = "categories",cascade = CascadeType.ALL)
    private List<Item> item;
}
