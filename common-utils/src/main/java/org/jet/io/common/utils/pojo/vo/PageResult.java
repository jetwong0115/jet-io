package org.jet.io.common.utils.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 3862453451286225289L;

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
    private T resultData;

    public PageResult() {
    }

    PageResult(long totalElements, long totalPages, boolean first, boolean last, boolean hasNext, boolean hasPrevious, T resultData) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.resultData = resultData;
    }

    public String toString() {
        return "PageResult(totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ", first=" + this.isFirst() + ", last=" + this.isLast() + ", hasNext=" + this.isHasNext() + ", hasPrevious=" + this.isHasPrevious() + ", resultData=" + this.getResultData() + ")";
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
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

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public T getResultData() {
        return resultData;
    }

    public static class PageResultBuilder<T> {
        private long totalElements;
        private long totalPages;
        private boolean first;
        private boolean last;
        private boolean hasNext;
        private boolean hasPrevious;

        PageResultBuilder() {
        }

        public PageResult.PageResultBuilder<T> totalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }


        public PageResult.PageResultBuilder<T> totalPages(long totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PageResult.PageResultBuilder<T> first(boolean first) {
            this.first = first;
            return this;
        }

        public PageResult.PageResultBuilder<T> last(boolean last) {
            this.last = last;
            return this;
        }

        public PageResult.PageResultBuilder<T> hasNext(boolean hasNext) {
            this.hasNext = hasNext;
            return this;
        }

        public PageResult.PageResultBuilder<T> hasPrevious(boolean hasPrevious) {
            this.hasPrevious = hasPrevious;
            return this;
        }


        public <E> PageResult<E> build(E resultData) {
            return new PageResult<>(this.totalElements, this.totalPages, this.first, this.last, this.hasNext, this.hasPrevious, resultData);
        }
    }

    public static <T> PageResultBuilder<T> builder() {
        return new PageResultBuilder<>();
    }


}
