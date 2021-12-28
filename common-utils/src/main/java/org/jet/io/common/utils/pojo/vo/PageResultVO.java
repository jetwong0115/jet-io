package org.jet.io.common.utils.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
@Deprecated
public class PageResultVO<VO> implements Serializable {

    private static final long serialVersionUID = 780613331365571473L;

    @JsonProperty("total_elements")
    private long totalElements;

    @JsonProperty("total_pages")
    private long totalPages;

    @JsonProperty("is_first")
    private boolean first;

    @JsonProperty("is_last")
    private boolean last;

    @JsonProperty("has_next")
    private boolean hasNext;

    @JsonProperty("has_previous")
    private boolean hasPrevious;

    @JsonProperty("result_data")
    private List<VO> resultData;


    public static  PageBuilder buildPage(Page<?> page) {
        PageData pageResult = new PageData(
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );

        return new DefaultPageBuilder<>(pageResult);
    }

    private static class PageData {
        private final long totalElements;
        private final long totalPages;
        private final boolean first;
        private final boolean last;
        private final boolean hasNext;
        private final boolean hasPrevious;

        private PageData(long totalElements, long totalPages, boolean first, boolean last, boolean hasNext, boolean hasPrevious) {
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.first = first;
            this.last = last;
            this.hasNext = hasNext;
            this.hasPrevious = hasPrevious;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public long getTotalPages() {
            return totalPages;
        }

        public boolean isFirst() {
            return first;
        }

        public boolean isLast() {
            return last;
        }

        public boolean hasNext() {
            return hasNext;
        }

        public boolean hasPrevious() {
            return hasPrevious;
        }
    }


    public interface PageBuilder<VO> {
        PageResultVO<VO> build(List<VO> eList);
    }

    static class DefaultPageBuilder<V> implements PageBuilder<V> {
        final PageData pageData;

        DefaultPageBuilder(PageData pageData) {
            this.pageData = pageData;
        }

        @Override
        public PageResultVO<V> build(List<V> eList) {
            PageResultVO<V> pageResult = new PageResultVO<>();
            pageResult.setTotalElements(pageData.getTotalElements());
            pageResult.setTotalPages(pageData.getTotalPages());
            pageResult.setFirst(pageData.isFirst());
            pageResult.setLast(pageData.isLast());
            pageResult.setHasNext(pageData.hasNext());
            pageResult.setHasPrevious(pageData.hasPrevious());
            pageResult.setResultData(eList);
            return pageResult;
        }
    }

    public List<VO> getResultData() {
        return resultData;
    }

    public void setResultData(List<VO> resultData) {
        this.resultData = resultData;
    }

    public long getTotalElements() {
        return totalElements;
    }

    protected void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    protected void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    protected void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    protected void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    protected void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    protected void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

}
