package controllers;

public record UserDTO(long card, int cvv, int pin, double balance, String name) {}