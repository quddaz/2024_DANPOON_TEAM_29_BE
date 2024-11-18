package com.globalnest.be.translation.dto;

public record Comment(
    String name,
    String content,
    String time
) {}