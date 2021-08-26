package org.jet.io.common.utils.pojo.vo;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PageResultVOMapper<T, E> {
    PageResultVO<T> buildPageResult(Page<E> page, List<T> vList);
//    PageResultVO<T> buildPageResult(Page<E> page, T t);
}
