package com.marjane.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AuthEntity {
    private String jwt;
    private UUID userId;
    private String authority;
}