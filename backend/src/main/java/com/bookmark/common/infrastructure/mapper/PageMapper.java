package com.bookmark.common.infrastructure.mapper;

import com.bookmark.common.domain.Paged;
import com.bookmark.common.domain.Pagination;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {
  public static <T, R> Paged<R> map(Page<T> source, Function<T, R> map) {
    List<R> content = source.getContent().stream().map(map).toList();

    return new Paged<R>(content, source.getNumber(), source.getSize(), source.getTotalElements(),
        source.getTotalPages(), source.isLast());
  }

  public static <T, R> Paged<R> map(Paged<T> source, Function<T, R> map) {
    List<R> content = source.content().stream().map(map).toList();

    return new Paged<R>(content, source.page(), source.size(), source.totalElements(),
        source.totalPages(), source.last());
  }

  public static Pageable map(Pagination pagination) {
    Sort sort = Sort.by(
        pagination.direction() == Pagination.Sort.ASC ? Sort.Direction.ASC : Sort.Direction.DESC,
        pagination.sortBy());

    return PageRequest.of(pagination.page(), pagination.size(), sort);
  }

  public static Pagination map(Pageable pageable) {
    Sort sort = pageable.getSort();

    return new Pagination(
        pageable.getPageNumber(), pageable.getPageSize(), "id", Pagination.Sort.ASC);
  }
}
