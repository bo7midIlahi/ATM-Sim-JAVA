package controllers;

public record TransactionDTO(
    int id,
    long card,
    String date,
    double moneyMoved,
    String sender,
    long receiverCard
) {}