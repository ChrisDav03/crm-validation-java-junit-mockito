package com.chrisdav03.crm.domain.model;

import java.time.LocalDate;

public class Lead {
    private final String id;
    private final String name;
    private final String nationalId;
    private final String email;
    private final LocalDate birthDate;

    public Lead(String id, String name, String nationalId, String email, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.email = email;
        this.birthDate = birthdate;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Lead [id=" + id + ", name=" + name + ", nationalId=" + nationalId
                + ", email=" + email + ", birthdate=" + birthDate + "]";
    }
}