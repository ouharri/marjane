package com.marjane.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class SuperAdmin extends AbstractUser {
    @OneToMany
    private List<Center> centers;
    @OneToMany
    private List<Admin> admins;
}
