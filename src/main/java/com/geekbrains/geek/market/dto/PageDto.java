package com.geekbrains.geek.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDto<T> {
    private long total;
    private List<T> content;
    private long size;

    public PageDto(List<T> content, long size) {
        this.content = content;
        this.total = content.size();
        this.size = size;
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }
}
