package com.finalprojectrest.entity;


import com.finalprojectrest.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "e_book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "is_active")
@SQLDelete(sql = "UPDATE e_book SET is_active = false WHERE id = ?")
public class EBook extends BaseEntity {

    @Column(name = "isbn", unique = true)
    String isbn;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Min(1000)
    @Max(9999)
    @Column(name = "year")
    Integer year;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "image")
    String image;

    @Enumerated(EnumType.STRING)
    Language language;

    @Column(name = "download_url")
    String downloadUrl;

}