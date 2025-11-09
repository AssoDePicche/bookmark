package com.bookmark.common.interfaces;

import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {
  public <T, R> Paged<R> map(Page<T> source, Function<T, R> map) {
    List<R> content = source.getContent().stream().map(map).toList();

    return new Paged<R>(content, source.getNumber(), source.getSize(), source.getTotalElements(),
        source.getTotalPages(), source.isLast());
  }
}
