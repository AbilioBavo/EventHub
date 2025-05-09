package com.stance.EventHub.dto.response;

public record LoginResponseDto(String token, String userType, String nome, Long id) {}