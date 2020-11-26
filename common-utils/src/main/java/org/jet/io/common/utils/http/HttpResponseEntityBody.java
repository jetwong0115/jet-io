package org.jet.io.common.utils.http;

import java.io.Serializable;
import java.util.Objects;

public abstract class HttpResponseEntityBody implements Serializable {
    static final int SUCCESS = 0;
    static final String SUCCESS_MSG = "OK";

    private final String logId;

    private final int code;

    private final String msg;

    HttpResponseEntityBody(String logId, int code, String msg) {
        this.logId = logId;
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getLogId() {
        return logId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpResponseEntityBody)) return false;
        HttpResponseEntityBody that = (HttpResponseEntityBody) o;
        return code == that.code &&
                Objects.equals(logId, that.logId) &&
                Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logId, code, msg);
    }

    @Override
    public String toString() {
        return "HttpClientBody{" +
                "logId=" + logId +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
