package com.finalprojectrest.entity;

import com.finalprojectrest.enums.Language;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;


@Entity(name = "Book")
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE books SET is_active = false WHERE id = ?")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {

    String isbn;
    @Column(length = 1000)
    String name;

    Integer stock;

    String author;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    Category category;

    String image;

    @Enumerated(EnumType.STRING)
    Language language;

    Integer year;

    Integer pages;

    String publishing;

    String covering;

    String purchaseGift;

    LocalDateTime accessDate;

    String location;

}
