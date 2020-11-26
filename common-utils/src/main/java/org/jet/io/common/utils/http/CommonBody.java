package org.jet.io.common.utils.http;

public class CommonBody extends HttpResponseBody {

    private CommonBody(String logId, int resCode, String resMsg) {
        super(logId, resCode, resMsg);
    }

    public static CommonBody success(String logId) {
        return new CommonBody(logId, SUCCESS, SUCCESS_MSG);
    }

    public static CommonBody msg(String logId, int resCode, String msg) {
        return new CommonBody(logId, resCode, msg);
    }


}
