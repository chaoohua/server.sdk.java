package io.rong.util;

import io.rong.models.CodeSuccessResult;

public class RcloudException extends Exception{

    public CodeSuccessResult result;

    public RcloudException(Exception e) {
        super(e);

    }
    public RcloudException(Integer code,String message) {
        super();
        this.result = new CodeSuccessResult(code,message);
    }
}
