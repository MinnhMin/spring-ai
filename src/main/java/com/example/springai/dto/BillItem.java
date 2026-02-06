package com.example.springai.dto;

public record BillItem(String itemName,
                       String unit,
                       Integer quantity,
                       Double price,
                       Double subTotal) {
}
