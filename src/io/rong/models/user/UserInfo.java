package io.rong.models.user;

/**
*
* 用户信息
* */
public class UserInfo {

    /**
     * userId:用户 Id，最大长度 64 字节.是用户在 App 中的唯一标识码，
     * 必须保证在同一个 App 内不重复，重复的用户 Id 将被当作是同一用户。（必传）
     */
    public String userId;
    /**
     * 用户名称，最大长度 128 字节。用来在 Push 推送时，显示用户的名称，
     * 刷新用户名称后 5 分钟内生效。（可选，提供即刷新，不提供忽略）
     */
    public String name;
    /**
     * portraitUri:用户头像 URI，最大长度 1024 字节。
     * 用来在 Push 推送时显示。（可选，提供即刷新，不提供忽略)
     */
    public String portraitUri;

    public UserInfo(String userId, String name,String portraitUri) {
        this.userId = userId;
        this.name = name;
        this.portraitUri = portraitUri;
    }


}
