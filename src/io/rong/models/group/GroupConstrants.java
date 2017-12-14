package io.rong.models.group;

public class GroupConstrants {
    public static final int GROUP_SYNC_OVERFLOW= 13001;//群组同步超限（每秒钟限 100 次）
    public static final int GROUP_JOIN_OVERFLOW = 13002;//用户加入群组数量超限（最大500）
    public static final int GROUP_USER_OVERFLOW = 13003;//群组用户超限（最大3000）
    public static final int GROUP_ROLLBACK_GAG_TIMEOUT = 13004;//群组禁言时间超限  最大43200分钟
    public static final int GROUP_ROLLBACK_GAG_NUMOUT = 13005;//解禁数量超限 （每次最多解禁 20 个用户）
    public static final int GROUP_PARAM_ERROR = 13006;//用户加入群组数量超限（最大500）
    public static final int GROUP_USER_ADD_OUT = 13007;//提交加入群组用户 Id 超限，单次最多不超过 1000 个用户。
    public static final int GROUP_ADD_GAG_OUT = 13008;//群组添加禁言时间超限  最大43200分钟
    public static final int GROUP_FORMIT_ERROR = 13009;//解禁数量超限 （每次最多解禁 20 个用户）

}