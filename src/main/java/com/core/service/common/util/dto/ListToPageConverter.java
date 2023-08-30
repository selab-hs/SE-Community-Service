package com.core.service.common.util.dto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class ListToPageConverter {
    public static <T> Page<T> convert(List<T> list, int page, int size) {
        int start = Math.min(page * size, list.size());
        int end = Math.min(start + size, list.size());

        return new PageImpl<>(list.subList(start, end), PageRequest.of(page, size), list.size());
    }
}
