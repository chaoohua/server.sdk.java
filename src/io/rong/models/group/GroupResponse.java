package io.rong.models.group;

import io.rong.messages.ErrorCodeMessage;
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
            switch (this.getCode()) {

                case RcloudServiceConstrants.RCLOUD_FREQUENCY_OUT:
                    this.setCode(GroupConstrants.GROUP_SYNC_OVERFLOW);
                    break;
                case RcloudServiceConstrants.RCLOUD_METHOD_LIMITED:
                    this.setCode(GroupConstrants.GROUP_JOIN_OVERFLOW);
                    break;
                default:
                    break;
            }
        if(this.getCode() != 200 && ErrorCodeMessage.errorCodeMaps.containsKey(this.getCode())){

            if(null == this.getErrorMessage() || "".equalsIgnoreCase(this.getErrorMessage())){
                this.setErrorMessage(ErrorCodeMessage.errorCodeMaps.get(this.getCode()));
            }
        }
        return GsonUtil.toJson(this, GroupResponse.class);
    }
}
