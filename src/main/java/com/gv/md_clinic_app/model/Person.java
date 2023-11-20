package com.gv.md_clinic_app.model;

import com.gv.md_clinic_app.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull @NotEmpty
    private String firstName;
    @NotNull @NotEmpty
    private String lastName;
    @Email @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String phone;
    @Embedded @NotNull
    private Address address;
    @NotNull @NotEmpty
    private Gender gender;
}
