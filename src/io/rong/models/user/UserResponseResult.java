package io.rong.models.user;

import io.rong.messages.ErrorCodeMessage;
import io.rong.models.ResponseResult;
import io.rong.util.GsonUtil;
import io.rong.util.RcloudServiceConstrants;

/**
 *  Group http 成功返回结果
 */
public class UserResponseResult extends ResponseResult {

    public UserResponseResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    @Override
    public String toString() {
            switch (this.getCode()) {

                case RcloudServiceConstrants.RCLOUD_FREQUENCY_OUT:
                    this.setCode(UserConstrants.USER_FREQUENCY_OUT);
                    break;
                case RcloudServiceConstrants.RCLOUD_METHOD_LIMITED:
                    this.setCode(UserConstrants.USER_NAME_LEN_OUT);
                    break;
                case RcloudServiceConstrants.RCLOUD_PARAM_LEN_OUT:
                    this.setCode(UserConstrants.USER_PARAM_ERROR);
                    break;
                default:
                    break;
            }
        if(this.getCode() != 200 && ErrorCodeMessage.errorCodeMaps.containsKey(this.getCode())){
            this.setErrorMessage(ErrorCodeMessage.errorCodeMaps.get(this.getCode()));
        }
        return GsonUtil.toJson(this, UserResponseResult.class);
    }
}
