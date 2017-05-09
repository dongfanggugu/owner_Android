package com.honyum.owner.net.base;

public class NetConstant {

    /**
     * 登陆
     */
    public static final String LOGIN = "smallOwnerLogin";

    /**
     * 退出登录
     */
    public static final String LOGOUT = "smallOwnerLogout";

    /**
     * 版本检测接口
     */
    public static final String URL_VERSION_CHECK = "checkVersion";

    /**
     * 小业主注册
     */
    public static final String OWNER_REGISTER = "addSmallOwner";

    /**
     * 请求所有的维保项目
     */
    public static final String GET_ALL_COMMUNITYS_BY_PROPERTY = "getAllCommunitysByProperty";

    /**
     * 添加报修
     */
    public static final String ADD_REPAIR = "addRepair";

    /**
     * 进行中的维修任务
     */
    public static final String UNDER_WAR_REPAIR = "getRepairBySmallOwner";

    /**
     * 已完成的维修任务
     */
    public static final String HISTORY_REPAIR = "getRepairBySmallOwnerComplete";

    /**
     * 小业主对维修的评价
     */
    public static final String RATING_REPAIR_WORKER = "editRepairBySmallOwner";

    /**
     * 小业主修改个人信息
     */
    public static final String EDIT_PERSON_INFO = "editSmallOwner";

    /**
     * 修改密码
     */
    public static final String EDIT_PASSWORD = "editSmallOwnerPwd";

    /**
     * 获取广告条
     */
    public static final String GET_BANNER = "getAdvertisementBySmallOwner";

    /**
     * 添加快修
     */
    public static final String ADD_REPAIR_ORDER = "addRepairOrder";

    /**
     * 获取维保类型
     */
    public static final String GET_MTTYPE_LIST = "getMainttypeList";

    /**
     * 获取短信验证码
     */
    public static final String GET_SMS_CODE = "getSMSCode";

    /**
     * 获取电梯品牌
     */
    public static final String GET_ELEVATOR_BRAND = "getBrandInfo";

    /**
     * 添加维保订单
     */
    public static final String ADD_MT_ORDER = "addMaintOrder";

    /**
     * 获取电梯故障类型
     */
    public static final String GET_REPAIR_TYPE_LIST = "getRepairTypeList";

    /**
     * 获取维保订单
     */
    public static final String GET_MT_ORDER_LIST = "getMaintOrderListByOwner";

    /**
     * 获取快修订单
     */
    public static final String GET_REPAIR_ORDER_LIST = "getRepairOrderListNotCompleteByOwner";

    /**
     * 获取完成的快修订单
     */
    public static final String GET_COMPLETE_REPAIR_ORDER_LIST = "getRepairOrderListCompleteByOwner";

    /**
     * 获取维保任务单
     */
    public static final String GET_MT_PROCESS_ORDER_LIST = "getMaintOrderProcessListByOrder";

    /**
     * 评价快修订单
     */
    public static final String RATING_REPAIR_ORDER = "saveRepairOrderEvaluate";

    /**
     * 获取快修任务单
     */
    public static final String GET_REPAIR_TASK_LIST = "getRepairOrderProcessListByOrder";

    /**
     * 确认维保任务单
     */
    public static final String CONFIRM_MT_TASK = "saveMaintOrderProcessConfirm";

    /**
     * 评价维保任务单
     */
    public static final String RATING_MT_TASK_ORDER = "saveMaintOrderProcessEvaluate";

    /**
     * 添加保障单
     */
    public static final String ADD_GUARANTEE = "addGuarantee";

    /**
     * 获取增值服务列表
     */
    public static final String GET_INCREMENT_LIST = "getIncrementTypeList";

    /**
     * 获取增值服务订购的订单列表
     */
    public static final String GET_INCREMENT_ORDER_LIST = "getIncrementListByOwner";

    /**
     * 获取增值服务订购的订单列表
     */
    public static final String GET_INCREMENT_ORDER_ORDER_LIST = "getPaymentBySmallOwnerOnIncrement";

    /**
     * 请求支付
     */
    public static final String CONTINUE_PAYMENT = "continuePayment";

    /**
     * 维保服务查看订单
     */
    public static final String MT_ORDER_ORDER_LIST = "getPaymentBySmallOwner";

    /**
     * 添加增值服务单
     */
    public static final String ADD_INCREMENT = "addIncrement";

    /**
     * 添加增值服务单
     */
    public static final String GET_MT_HISTORY_ORDER = "getMaintOrderHistoryList";

    /**
     * 获取增值服务历史订单
     */
    public static final String GET_INCREMENT_HISTORY_ORDER_LIST = "getIncrementHistoryListByOwner";
}
