package io.rong.models.group;

import io.rong.messages.ErrorCodeMessage;
import io.rong.models.CommonConstrants;
import io.rong.models.ResponseResult;
import io.rong.util.GsonUtil;
import io.rong.util.RcloudServiceConstrants;

/**
 *  Group http 成功返回结果
 */
public class GroupResponse extends ResponseResult {

    public GroupResponse(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public GroupResponse() {
        super();
    }


    @Override
    public String toString() {
        return GsonUtil.toJson(this, GroupResponse.class);
    }
}
