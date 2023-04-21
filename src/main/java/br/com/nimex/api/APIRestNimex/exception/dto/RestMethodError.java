package br.com.nimex.api.APIRestNimex.exception.dto;

public record RestMethodError(Integer status, String methodNotAllowed, String message) { }
