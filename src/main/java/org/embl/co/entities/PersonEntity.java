package org.embl.co.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.Entity;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "First Name is mandatory")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last Name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Age is required")
    @Column(name = "age")
    private Integer age;

    @NotNull(message = "Favourite color is required")
    @Column(name = "favourite_color")
    private String favouriteColor;
}
