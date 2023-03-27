package com.test.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equation")
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Equation name cannot be blank")
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @NotBlank(message = "Equation content cannot be blank")
    @Column(name = "content", length = 512)
    private String content;

    @NotNull(message = "Equation answer cannot be blank")
    @Column(name = "answer")
    private Double answer;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equation other = (Equation) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
}
