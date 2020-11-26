package org.jet.io.common.utils.http;

import java.util.Objects;
import java.util.StringJoiner;

public class ResultBody<T extends Object> extends HttpResponseEntityBody {

    private final T data;

    private ResultBody(String logId, int code, String msg, T data) {
        super(logId, code, msg);
        this.data = data;
    }

    public static ResultBody<Object> result(String logId, Object data) {
        return new ResultBody<>(logId, SUCCESS, SUCCESS_MSG, data);
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultBody)) return false;
        if (!super.equals(o)) return false;
        ResultBody<?> that = (ResultBody<?>) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResultBody.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .toString();
    }
}
