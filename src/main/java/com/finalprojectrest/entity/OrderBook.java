package com.finalprojectrest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE orders_books SET is_active = false WHERE id = ?")
//@EqualsAndHashCode(of="id")
@Data
@Entity
@Table(name = "orders_books")
public class OrderBook extends BaseEntity{
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;
    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBook orderBook)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(((OrderBook) o).getId(),this.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getId());
    }
}
