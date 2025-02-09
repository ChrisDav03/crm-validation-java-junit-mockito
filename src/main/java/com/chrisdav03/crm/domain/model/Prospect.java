package com.chrisdav03.crm.domain.model;


import java.time.LocalDate;

public class Prospect {
    private final String id;
    private final String name;
    private final String nationalId;
    private final String email;
    private final LocalDate birthDate;

    public Prospect(String id, String name, String nationalId, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.nationalId = nationalId;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getEmail() {
        return email;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    @Override
    public String toString() {
        return "Prospect{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

