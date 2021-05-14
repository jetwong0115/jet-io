package org.jet.io.common.utils.pojo.vo;

import org.springframework.data.domain.Page;

import java.util.List;


public class PageResultVOMapperImpl<V, E> implements PageResultVOMapper<V, E> {

    @Override
    public PageResultVO<V> buildPageResult(Page<E> page, List<V> vList) {
        PageResultVO<V> pageResult = new PageResultVO<>();
        pageResult.setResultData(vList);
        pageResult.setTotalElements(page.getTotalElements());
        pageResult.setTotalPages(page.getTotalPages());
        pageResult.setFirst(page.isFirst());
        pageResult.setLast(page.isLast());
        pageResult.setHasNext(page.hasNext());
        pageResult.setHasPrevious(page.hasPrevious());
        return pageResult;
    }

}
