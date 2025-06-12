package com.finalprojectrest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.util.Objects;
import java.util.Set;

@Table(name="students")
//@EqualsAndHashCode(of = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@SQLDelete(sql = "UPDATE students SET is_active = false WHERE id = ?")
@Entity(name = "Student")
public class Student extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;


    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(nullable = false,name = "group_id")
    private Group group;

    @Column(length = 7, nullable = false)
    private String studentFIN;

    @OneToMany(mappedBy = "student", cascade ={CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Set<Order> orders;

    private String email;

    private String password;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
