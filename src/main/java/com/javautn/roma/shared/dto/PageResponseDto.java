package com.javautn.roma.shared.dto;

public class PageResponseDto<T> {
    private T data = null;
    private int page = 0;
    private int totalPages = 0;

    public PageResponseDto(T data, int page, int totalPages) {
        this.data = data;
        this.page = page;
        this.totalPages = totalPages;
    }

    public T getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
