package com.google.entity;

import lombok.Data;

@Data
public class Account {
    int id;
    String username;
    String password;
    String role;
}
