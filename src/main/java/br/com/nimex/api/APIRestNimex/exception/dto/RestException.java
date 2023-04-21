package br.com.nimex.api.APIRestNimex.exception.dto;

public record RestException (Integer status, String error, String message){ }
