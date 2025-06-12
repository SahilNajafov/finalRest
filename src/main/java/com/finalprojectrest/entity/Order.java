package com.finalprojectrest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;



@SQLDelete(sql = "UPDATE orders SET is_active = false WHERE id = ?")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(
//            name = "orders_books",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    Set<Book> books;
        @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    Set<OrderBook> books;


    @JoinColumn(name = "student_id", nullable = false)
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    Student student;

    @Column(nullable = false)
    Boolean inProgress;

    @Column(nullable = false)
    Boolean inDelay;

    LocalDate finishedAt;

    @PrePersist
    private void init() {
        this.setCreatedAt(LocalDateTime.now());
        this.setInDelay(false);
        this.setInProgress(true);
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
