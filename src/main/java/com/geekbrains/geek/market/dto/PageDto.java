package com.geekbrains.geek.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class PageDto<T> {
    private long total;
    private ArrayList<T> content;
    private int size;

    public PageDto(ArrayList<T> content, int size) {
        this.content = content;
        this.total = content.size();
        this.size = size;
    }

    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }
}
