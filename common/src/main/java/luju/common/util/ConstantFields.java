package luju.common.util;

public class ConstantFields {
    /* 分页中每页数据数 */
    public static final int DEFAULT_PAGE_SIZE = 10;
    /* ajax分页中数据的KEY */
    public static final String PAGE_KEY = "page";
    /* 消息操作key*/
    public static final String OPERATION_MESSAGE = "message";
    /*转弯桥*/
    public  static  final String ZWQ = "ZWQ";
    /*转弯桥*/
    public  static  final String DC = "DC";
    /*转弯桥*/
    public  static  final String YH = "YH";

    /* 添加成功消息*/
    public static final String ADD_SUCCESS_MESSAGE = "添加成功";
    /* 添加失败消息*/
    public static final String ADD_FAILURE_MESSAGE = "添加失败";
    /* 编辑成功消息*/
    public static final String EDIT_SUCCESS_MESSAGE = "编辑成功";
    /* 编辑失败消息*/
    public static final String EDIT_FAILURE_MESSAGE = "编辑失败";
    /* 删除成功消息*/
    public static final String DELETE_SUCCESS_MESSAGE = "删除成功";
    /* 删除失败消息*/
    public static final String DELETE_FAILURE_MESSAGE = "删除失败";

    /* 接车推演时间*/
    public static long JC_TIME = 480000;
    /* 时间差*/
    public static long TIME = 600000;

    /* 解体推演时间*/
    public static long JT1_TIME = 600000;
    public static long JT2_TIME = 960000;
    /*下直推演时间*/
    public static long XZ_TIME = 240000;
    /*上直推演时间*/
    public static long SZ_TIME = 240000;

    /* 解体推演时间*/
    public static long ZM1_TIME = 600000;
    public static long ZM2_TIME = 1200000;
    public static long ZM3_TIME = 600000;
    public static long ZM4_TIME = 900000;
    public static long ZM5_TIME = 1500000;

    /* 本务机推演时间*/
    public static long BWJ1_TIME = 240000;
    public static long BWJ2_TIME = 540000;

    /* 挑车推演时间*/
    public static long TC_TIME = 600000;

    /* 转场推演时间*/
    public static long ZC_TIME = 600000;

    /* 作业类别*/
    public static final String TYPE_JC = "接车";
    public static final String TYPE_TC = "TC";
    public static final String TYPE_BWJ = "本务机";
    public static final String TYPE_QCX = "牵出线";
    public static final String TYPE_JDX = "机待线";
    public static final String TYPE_ZC = "上行编发场";

    /* 作业类别*/
    public static final String BWJDS = "南入库（立折）";
    public static final String BWJDN = "北入库";

    /* 记事栏内容*/
    public static final String CX = "超限";
    public static final String JF = "禁峰列车";
    public static final String CC = "超长";
    public static final String XD = "XD";

    /* 重点事项内容*/
    public static final String IMPORTANT_CX = "必须接入XD02或XD05道";
    public static final String IMPORTANT_JF = "优先接入XD02道，XD03道，XD04道";
    public static final String IMPORTANT_CC = "4、5道接车";

    /* 作业类别缩写*/

    public static final String J = "J";
    public static final String N = "N";
    public static final String S = "S";
    public static final String QC = "QC";
    public static final String JD = "JD";
    public static final String XT1 = "XT1";//南
    public static final String XT2 = "XT2";//北
    public static final String JDZ = "Z";
    public static final String JTD = "D";
    public static final String JTY = "Y";
    public static final String T404 = "T404";
    public static final String T402 = "T402";


    /* 8.17 new data type */

    public static final String JC = "JC";
    public static final String BWJ = "BWJ";
    public static final String JT = "JT";
    public static final String ZC = "ZC";
    public static final String ZM = "ZM";
    public static final String JCSOURCE = "MSJ";
    public static final String JCSOURCEC = "马三家";
    public static final String ZCSOURCE = "SXBFC";
    public static final String ZCSOURCEC = "上行编发场";

    /* 上到数据常量*/
    public static final String ZWQSOURCE = "ZWQ";
    public static final String DCSOURCE = "DC";
    public static final String YHSOURCE = "YH";



    /**
     * 默认邮件配置项
     */
    public static final String MAIL_DEFAULT_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_DEFAULT_AUTH_ENABLE_KEY = "mail.smtp.auth";
    public static final String MAIL_DEFAULT_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_DEFAULT_USER_KEY = "mail.smtp.user";
    public static final String MAIL_DEFAULT_USER_PASS_KEY = "mail.smtp.pass";
    public static final String MAIL_DEFAULT_STARTTLS_ENABLE_KEY = "mail.smtp.starttls.enable";

    /* shiro登录状态Key */
    public static final String LOGIN_KEY = "shiroLogin";
    /* 用户登录后信息 */
    public static final String SESSION_LOGIN_KEY = "userLoginInfo";
    public static final String REQUEST_NAME_KEY = "loginName";
    public static final String REQUEST_PSWD_KEY = "password";
    /* 工人登录后信息 */
    public static final String SESSION_WORKER_LOGIN_KEY = "workerInfo";

    /* 日志查询条件存储 */
    public static final String LOG_QUERY = "logQuery";

    /* 操作提示信息 */
    public static final String OPERATION_MESSAGE_KEY = "opsKey";
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final String FAILURE_MESSAGE = "操作失败";;

    /* 命令文件格式长度 */
    public static final int COMMAND_FILE_TITLE_LENGTH = 1;
    public static final int COMMAND_FILE_SUBTITLE_LENGTH = 3;
    public static final int COMMAND_FILE_DESCRIPTION_LENGTH = 12;
    public static final int COMMAND_FILE_ITEM_LENGTH = 2;

    /* 错误状态码 */
    public static final String NO_FILE_ERROR_CODE = "100";
    public static final String NO_CONTENT_IN_FILE_ERROR_CODE = "101";
    public static final String NO_COMMAND_NUMBER_IN_FILE_ERROR_CODE = "102";
    public static final String COMMAND_FILE_COMMAND_NUMBER_REPEAT = "103";
    public static final String NO_FILE_UPLOAD_ERROR_CODE = "104";
    public static final String STORE_COMMAND_FILE_ERROR_CODE = "105";
    public static final String COMMAND_EXCEL_FILE_REPEAT_ERROR_CODE = "106";
    public static final String ADD_COMMAND_FILE_RECORD_ERROR_CODE = "107";
    public static final String COMMAND_FILE_COMMAND_RECORD_ERROR_CODE = "108";

    public static final String PREVIEW_EXCEL_SUCCESS_CODE = "200";

    /* 状态码的key */
    public static final String SYSTEM_STATUS_CODE_KEY = "code";

    /* 如果用户没有关联站,命令文件默认的目录名 */
    public static final String USER_NO_STATION_DEFAULT_STATION_NAME = "other";

    /* 命令excel文件中每项的分隔符 */
    public static final String COMMAND_EXCEL_FILE_OPTION_SPLIT_CHAR = ":";

    /* 命令文件导入时,命令项是否匹配关键字的预览条目选中状态 */
    public static final int EXCEL_PREVIEW_COMAND_OPTION_SELECTED = 1;
    public static final int EXCEL_PREVIEW_COMMAND_OPTION_UNSELECTED = 0;

    /* 站相关的关键字和不包含的相似的关键字的分隔符 */
    public static final String STATION_COMMAND_OPTION_KEYWORDS_ADD_SPLIT_CHAR = ",";

    /* 数据字典中字段定义的常量 */
    public static final String DICTIONARY_RECORD_STATE_GROUP_VALUE = "recordState";
    public static final String COMMAND_EXCEL_IMPORT_RECORD_DEFAULT_STATE_TEXT = "未解读";
    public static final String DICTIONARY_DISPATCH_COMMAND_STATE_GROUP_VALUE = "commandState";
    public static final String DICTIONARY_DISPATCH_WORK_ORDER_GROUP_VALUE = "workOrder";
    public static final String DICTIONARY_WORK_ORDER_GROUP_VALUE = "workOrder";
    public static final String DICTIONARY_PRIORITIES_RECORD_GROUP_VALUE = "recordCommandState";

    /* 另有命令时至的开始时间 */
    public static final String DEFAULT_ONLY_BEGIN_STOP_DATE = "1990-01-01";
    public static final String DEFAULT_ONLY_BEGIN_STOP_DATE_ADD = "01/01/1990";

    /* 一级命令解读的命令状态 */
    public static final int COMMAND_LOG_ADD_COMMAND_STATE = 1; // 添加
    public static final int COMMAND_LOG_APPROVE_PASS_COMMAND_STATE = 2; // 审验通过
    public static final int COMMAND_LOG_APPROVE_REJECT_COMMAND_STATE = 3; // 审验拒绝
    public static final int COMMAND_LOG_DELETE_REDO_COMMAND_STATE = 4; // 重做删除
    public static final int COMMAND_LOG_DELETE_DELETE_COMMAND_STATE = 5; // 命令删除
    public static final int COMMAND_LOG_DONE_COMMAND_STATE = 6; // 命令完成
    public static final int COMMAND_STATE_REDO_DONE_STATE = 7; // 已重做

    /* 系统目录的分割符 */
    public static final String SYSTEM_FILE_PATH_SPLIT_CHAR = "/";
    
    /* excel解读命令是否与录入站关联 1:是关联 0:不关联 */
    public static final int EXCEL_COMMAND_IS_STATION_RECORD = 1;
    public static final int EXCEL_COMMAND_NOT_STATION_RECORD = 0;

    /* 命令执行日志的类型 */
    public static final int COMMAND_DATE_EXECUTE_TYPE_ONE_DAY = 1; // 单日
    public static final int COMMAND_DATE_EXECUTE_TYPE_RANGE_DAY = 2; // 时间段
    public static final int COMMAND_DATE_EXECUTE_TYPE_ONLY_BEGIN_DAY = 3; // 另有命令是止

    /* 重点事项非必填选项的默认值 */
    public static final String DEFAULT_PRIORITIES_RECORD_COMMAND_VALUE = "0";

    /* 重点事项记录状态 */
    public static final int PRIORITIES_RECORD_ADD_STATE = 1;
    public static final int PRIORITIES_RECORD_DELETE_STATE = 2;

    /* 系统配置中重点事项配置开关 */
    public static final String SYSTEM_CONFIG_KEY = "prioritiesTurn";

    /* 重点事项和命令解读日视图和月视图开关 */
    public static final int COMMAND_AND_PRIORITIES_TURN_ON = 0;
}
