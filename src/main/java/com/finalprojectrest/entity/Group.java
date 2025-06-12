package com.finalprojectrest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

import java.util.List;
import java.util.Objects;


@SQLDelete(sql = "UPDATE groups SET is_active = false WHERE id = ?")
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Group extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group",cascade = {CascadeType.REFRESH})
    private List<Student> students;
    @Column(name = "faculty")
    private String faculty;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), group.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
