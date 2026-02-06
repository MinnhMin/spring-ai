package com.example.springai.dto;

public record ExpenseInfo(
        String category,
        String itemName,
        Double amount
) {
}
