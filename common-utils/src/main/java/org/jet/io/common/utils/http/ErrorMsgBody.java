package org.jet.io.common.utils.http;

public class ErrorMsgBody extends HttpResponseBody {

    private ErrorMsgBody(String logId, int resCode, String resMsg) {
        super(logId, resCode, resMsg);
    }

    public static ErrorMsgBody errorMsg(String logId, int resCode, String msg) {
        return new ErrorMsgBody(logId, resCode, msg);
    }


}
