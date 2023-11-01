package com.marjane;

import com.marjane.Repositories.UserRepository;

public class Main {

    public static void main(String[] args) {
        new UserRepository().getAll().forEach(System.out::println);
    }
}
