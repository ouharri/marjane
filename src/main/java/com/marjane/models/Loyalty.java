package com.marjane.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID loyaltyId;

    @OneToOne
    private User user;

    //TODO Add loyalty-specific fields
}
