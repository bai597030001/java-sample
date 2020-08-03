package com.example.designmode.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: java-sample
 * @description: sms
 * @author: baijd-a
 * @create: 2020-07-29 10:44
 **/
public class SMSTest {

    public static final Map<String, String> CODE = new HashMap<String, String>(16){{
        // 0.accesskey accessSecret错误
        // 1.账户异常（不存在/未激活/已冻结）
        // 2.RAM权限不足
        // 3.账户余额不足
        // 4.短信签名错误
        // 5.短信模板错误
        // 6.手机号码错误/超出限制
        // 7.模板参数错误
        // 8.参数超出长度限制
        // 9.签名（Signature）加密错误
        // 10.时间戳错误
    }};

    /**
     * 公共应答参数状态码
     **/
    public static final Map<String, String> RESPONSE_CODE_STATUS = new HashMap<String, String>(32) {{
        put("OK", "success");
        put("SMS_SIGNATURE_SCENE_ILLEGAL", "短信所使用签名场景非法");
        put("EXTEND_CODE_ERROR", "扩展码使用错误，相同的扩展码不可用于多个签名");
        put("DOMESTIC_NUMBER_NOT_SUPPORTED", "国际/港澳台消息模板不支持发送境内号码");
        put("DENY_IP_RANGE", "被系统检测到源IP属于非中国大陆地区");
        put("DAY_LIMIT_CONTROL", "触发日发送限额");
        put("SMS_CONTENT_ILLEGAL", "短信内容包含禁止发送内容");
        put("SMS_SIGN_ILLEGAL", "签名禁止使用");
        put("RAM_PERMISSION_DENY", "RAM权限不足");
        put("OUT_OF_SERVICE", "业务停机: 余额不足");
        put("PRODUCT_UN_SUBSCRIPT", "未开通云通信产品");
        put("PRODUCT_UNSUBSCRIBE", "产品未开通");
        put("ACCOUNT_NOT_EXISTS", "账户不存在");
        put("ACCOUNT_ABNORMAL", "账户异常");
        put("SMS_TEMPLATE_ILLEGAL", "短信模版不合法");
        put("SMS_SIGNATURE_ILLEGAL", "短信签名不合法");
        put("INVALID_PARAMETERS", "参数异常");
        put("SYSTEM_ERROR", "系统错误: 请重新调用接口");
        put("MOBILE_NUMBER_ILLEGAL", "非法手机号");
        put("MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制");
        put("TEMPLATE_MISSING_PARAMETERS", "模版缺少变量");
        put("BUSINESS_LIMIT_CONTROL", "业务限流");
        put("INVALID_JSON_PARAM", "模板参数中JSON参数不合法");
        put("BLACK_KEY_CONTROL_LIMIT", "黑名单管控：变量内容含有限制发送的内容，例如变量中不允许透传URL");
        put("PARAM_LENGTH_LIMIT", "参数超出长度限制(每个变量的长度限制为1~20字符)");
        put("PARAM_NOT_SUPPORT_URL", "不支持URL");
        put("AMOUNT_NOT_ENOUGH", "账户余额不足");
        put("TEMPLATE_PARAMS_ILLEGAL", "模版变量里包含非法关键字");
        put("SignatureDoesNotMatch", "签名（Signature）加密错误");
        put("InvalidTimeStamp.Expired", "一般由于时区差异造成时间戳错误，发出请求的时间和服务器接收到请求的时间不在15分钟内。阿里云网关使用的时间是GMT时间");
        put("SignatureNonceUsed", "唯一随机数重复，SignatureNonce为唯一随机数，用于防止网络重放攻击");
        put("InvalidVersion", "版本号（Version）错误：短信服务的API版本号（Version）为2017-05-25");
        put("InvalidAction.NotFound", "参数Action中指定的接口名错误");
        put("SIGN_COUNT_OVER_LIMIT", "一个自然日中申请签名数量超过限制");
        put("TEMPLATE_COUNT_OVER_LIMIT", "一个自然日中申请模板数量超过限制");
        put("SIGN_NAME_ILLEGAL", "签名名称不符合规范");
        put("SIGN_FILE_LIMIT", "签名认证材料附件大小超过限制");
        put("SIGN_OVER_LIMIT", "签名的名称或申请说明的字数超过限制");
        put("TEMPLATE_OVER_LIMIT", "模板的名称、内容或申请说明的字数超过限制");
        put("SIGNATURE_BLACKLIST", "签名内容涉及违规信息");
        put("InvalidAccessKeyId.NotFound", "Specified access key is not found.");
    }};


    /**
     * 阿里云应答参数状态码
     * key: 阿里云发送短信SendSms应答状态码Code
     * valye: 自定义统一SendSms应答状态吗Code
     **/
    private static final Map<String, String> ALI_RESPONSE_CODE_STATUS = new HashMap<String, String>(64){{
        put("OK", "OK");
        put("isv.SMS_SIGNATURE_SCENE_ILLEGAL", "SMS_SIGNATURE_SCENE_ILLEGAL");
        put("isv.EXTEND_CODE_ERROR", "EXTEND_CODE_ERROR");
        put("isv.DOMESTIC_NUMBER_NOT_SUPPORTED", "DOMESTIC_NUMBER_NOT_SUPPORTED");
        put("isv.DENY_IP_RANGE", "DENY_IP_RANGE");
        put("isv.DAY_LIMIT_CONTROL", "DAY_LIMIT_CONTROL");
        put("isv.SMS_CONTENT_ILLEGAL", "SMS_CONTENT_ILLEGAL");
        put("isv.SMS_SIGN_ILLEGAL", "SMS_SIGN_ILLEGAL");
        put("isp.RAM_PERMISSION_DENY", "RAM_PERMISSION_DENY");
        put("isv.OUT_OF_SERVICE", "OUT_OF_SERVICE");
        put("isv.PRODUCT_UN_SUBSCRIPT", "PRODUCT_UN_SUBSCRIPT");
        put("isv.PRODUCT_UNSUBSCRIBE", "PRODUCT_UNSUBSCRIBE");
        put("isv.ACCOUNT_NOT_EXISTS", "ACCOUNT_NOT_EXISTS");
        put("isv.ACCOUNT_ABNORMAL", "ACCOUNT_ABNORMAL");
        put("isv.SMS_TEMPLATE_ILLEGAL", "SMS_TEMPLATE_ILLEGAL");
        put("isv.SMS_SIGNATURE_ILLEGAL", "SMS_SIGNATURE_ILLEGAL");
        put("isv.INVALID_PARAMETERS", "INVALID_PARAMETERS");
        put("isp.SYSTEM_ERROR", "SYSTEM_ERROR");
        put("isv.MOBILE_NUMBER_ILLEGAL", "MOBILE_NUMBER_ILLEGAL");
        put("isv.MOBILE_COUNT_OVER_LIMIT", "MOBILE_COUNT_OVER_LIMIT");
        put("isv.TEMPLATE_MISSING_PARAMETERS", "TEMPLATE_MISSING_PARAMETERS");
        put("isv.BUSINESS_LIMIT_CONTROL", "BUSINESS_LIMIT_CONTROL");
        put("isv.INVALID_JSON_PARAM", "INVALID_JSON_PARAM");
        put("isv.BLACK_KEY_CONTROL_LIMIT", "BLACK_KEY_CONTROL_LIMIT");
        put("isv.PARAM_LENGTH_LIMIT", "PARAM_LENGTH_LIMIT");
        put("isv.PARAM_NOT_SUPPORT_URL", "PARAM_NOT_SUPPORT_URL");
        put("isv.AMOUNT_NOT_ENOUGH", "AMOUNT_NOT_ENOUGH");
        put("isv.TEMPLATE_PARAMS_ILLEGAL", "TEMPLATE_PARAMS_ILLEGAL");
        put("SignatureDoesNotMatch", "SignatureDoesNotMatch");
        put("InvalidTimeStamp.Expired", "InvalidTimeStamp.Expired");
        put("SignatureNonceUsed", "SignatureNonceUsed");
        put("InvalidVersion", "InvalidVersion");
        put("InvalidAction.NotFound", "InvalidAction.NotFound");
        put("isv.SIGN_COUNT_OVER_LIMIT", "SIGN_COUNT_OVER_LIMIT");
        put("isv.TEMPLATE_COUNT_OVER_LIMIT", "TEMPLATE_COUNT_OVER_LIMIT");
        put("isv.SIGN_NAME_ILLEGAL", "SIGN_NAME_ILLEGAL");
        put("isv.SIGN_FILE_LIMIT", "SIGN_FILE_LIMIT");
        put("isv.SIGN_OVER_LIMIT", "SIGN_OVER_LIMIT");
        put("isv.TEMPLATE_OVER_LIMIT", "TEMPLATE_OVER_LIMIT");
        put("SIGNATURE_BLACKLIST", "SIGNATURE_BLACKLIST");
        put("InvalidAccessKeyId.NotFound", "InvalidAccessKeyId.NotFound");
    }};


    /**
     * 华为云应答参数状态码
     * key: 阿里云发送短信SendSms应答状态码Code
     * valye: 自定义统一SendSms应答状态吗Code
     **/
    private static final Map<String, String> HUAWEI_RESPONSE_CODE_STATUS = new HashMap<String, String>(64) {{
        put("000000", "请求成功");
        put("E000000", "系统异常，一般是请求格式异常，短信平台无法解析");
        put("E000001", "HTTP消息头未找到Authorization字段");
        put("E000002", "Authorization字段中未找到realm属性");
        put("E000003", "Authorization字段中未找到profile属性");
        put("E000004", "Authorization中realm属性值应该为“SDP”");
        put("E000005", "Authorization中profile属性值应该为“UsernameToken”");
        put("E000006", "Authorization中type属性值应该为“Appkey”");
        put("E000007", "Authorization字段中未找到type属性");
        put("E000008", "Authorization中没有携带WSSE");
        put("E000020", "HTTP头未找到X-WSSE字段");
        put("E000021", "X-WSSE字段中未找到UserName属性");
        put("E000022", "X-WSSE字段中未找到Nonce属性");
        put("E000023", "X-WSSE字段中未找到Created属性");
        put("E000024", "X-WSSE字段中未找到PasswordDigest属性");
        put("E000025", "Created属性格式错误");
        put("E000026", "X-WSSE字段中未找到UsernameToken属性");
        put("E000027", "非法请求");
        put("E000040", "ContentType值应该为application/x-www-form-urlencoded");
        put("E000101", "鉴权失败");
        put("E000102", "app_key无效");
        put("E000103", "app_key不可用");
        put("E000104", "app_secret无效");
        put("E000105", "PasswordDigest无效");
        put("E000106", "app_key没有调用本API的权限");
        put("E000109", "用户状态未激活");
        put("E000110", "时间超出限制（请确认X-WSSE鉴权时，生成随机数的时间（Created）与发送请求时的本地时间不能相差太大（具体差值请与管理员确认）。请确认服务器的本地时间是否正确，建议不要与北京时间相差大于8小时。请检查随机数生成时间（Created）的代码实现，以Java为例，将格式字符串中的YYYY改为yyyy）");
        put("E000111", "用户名或密码错误");
        put("E000112", "用户状态已冻结");
        put("E000503", "参数格式错误");
        put("E000510", "短信发送失败，描述见参数status");
        put("E000620", "对端app IP不在白名单列表中");
        put("E000623", "SP短信发送量达到限额");
    }};

    private static final Map<String, String> HUAWEI_RESPONSE_STATUS = new HashMap<String, String>(16){{
        put("E200015", "待发送短信数量太大");
        put("E200028", "模板变量校验失败");
        put("E200029", "模板类型校验失败");
        put("E200030", "模板未激活");
        put("E200031", "协议校验失败");
        put("E200033", "模板类型不正确");
        put("E200041", "同一短信内容接收号码重复");
    }};

    /**
     * 腾讯云应答参数状态码
     * key: 阿里云发送短信SendSms应答状态码Code
     * valye: 自定义统一SendSms应答状态吗Code
     **/
    private static final Map<String, String> TENCENT_RESPONSE_CODE_STATUS = new HashMap<String, String>(128) {{
        // 公共错误码
        put("UnsupportedOperation", "操作不支持");
        put("ResourceInUse", "资源被占用");
        put("InternalError", "内部错误");
        put("RequestLimitExceeded", "请求的次数超过了频率限制");
        put("AuthFailure.SecretIdNotFound", "密钥不存在");
        put("LimitExceeded", "超过配额限制");
        put("NoSuchVersion", "接口版本不存在");
        put("ResourceNotFound", "资源不存在");
        put("AuthFailure.SignatureFailure", "签名错误");
        put("AuthFailure.SignatureExpire", "签名过期（Timestamp 和服务器时间相差不得超过五分钟，请检查本地时间是否和标准时间同步）");
        put("UnsupportedRegion", "接口不支持所传地域");
        put("UnauthorizedOperation", "未授权操作");
        put("InvalidParameter", "参数错误");
        put("ResourceUnavailable", "资源不可用");
        put("AuthFailure.MFAFailure", "MFA 错误");
        put("AuthFailure.UnauthorizedOperation", "请求未授权");
        put("AuthFailure.InvalidSecretId", "密钥非法（不是云 API 密钥类型）");
        put("AuthFailure.TokenFailure", "token 错误");
        put("DryRunOperation", "DryRun 操作，代表请求将会是成功的，只是多传了 DryRun 参数");
        put("FailedOperation", "操作失败");
        put("UnknownParameter", "未知参数错误");
        put("UnsupportedProtocol", "HTTP(S)请求协议错误，只支持 GET 和 POST 请求");
        put("InvalidParameterValue", "参数取值错误");
        put("InvalidAction", "接口不存在");
        put("MissingParameter", "缺少参数错误");
        put("ResourceInsufficient", "资源不足");
        // 业务错误码
        put("FailedOperation.ContainSensitiveWord", "短信内容中含有敏感词，请联系 sms helper");
        put("FailedOperation.FailResolvePacket", "请求包解析失败，通常情况下是由于没有遵守 API 接口说明规范导致的");
        put("FailedOperation.InsufficientBalanceInSmsPackage", "套餐包余量不足，请 购买套餐包");
        put("FailedOperation.JsonParseFail", "解析请求包体时候失败");
        put("FailedOperation.MarketingSendTimeConstraint", "营销短信发送时间限制，为避免骚扰用户，营销短信只允许在8点到22点发送");
        put("FailedOperation.MissingSignature", "没有申请签名之前，无法申请模板，请根据 创建签名 申请完成之后再次申请");
        put("FailedOperation.MissingSignatureToModify", "此签名 ID 未提交申请或不存在，不能进行修改操作，请检查您的 SignId 是否填写正确");
        put("FailedOperation.MissingTemplateToModify", "此模板 ID 未提交申请或不存在，不能进行修改操作，请检查您的 TemplateId是否填写正确");
        put("FailedOperation.NotEnterpriseCertification", "非企业认证无法使用签名及模版相关接口，您可以 变更实名认证模式，变更为企业认证用户后，约1小时左右生效");
        put("FailedOperation.OtherError", "其他错误，一般是由于参数携带不符合要求导致，请参看API接口说明，如有需要请联系 sms helper");
        put("FailedOperation.PhoneNumberInBlacklist", "手机号在黑名单库中，通常是用户退订或者命中运营商黑名单导致的，可联系 sms helper 解决");
        put("FailedOperation.PhoneNumberOnBlacklist", "手机号在黑名单库中，通常是用户退订或者命中运营商黑名单导致的，可联系 sms helper 解决");
        put("FailedOperation.SignatureIncorrectOrUnapproved", "签名格式错误或者签名未审批，签名只能由中英文、数字组成，要求2 - 12个字");
        put("FailedOperation.TemplateAlreadyPassedCheck", "此模板已经通过审核，无法再次进行修改");
        put("FailedOperation.TemplateIncorrectOrUnapproved", "模版未审批或请求的内容与审核通过的模版内容不匹配，请参考 1014错误详解");
        put("InternalError.OtherError", "其他错误，请联系 sms helper 并提供失败手机号");
        put("InternalError.RequestTimeException", "请求发起时间不正常，通常是由于您的服务器时间与腾讯云服务器时间差异超过10分钟导致的，请核对服务器时间及 API 接口中的时间字段是否正常");
        put("InternalError.RestApiInterfaceNotExist", "不存在该 RESTAPI 接口，请核查 REST API 接口说明");
        put("InternalError.SigFieldMissing", "后端包体中请求包体没有 Sig 字段或 Sig 为空");
        put("InternalError.SigVerificationFail", "后端校验 Sig 失败");
        put("InternalError.Timeout", "请求下发短信超时，请参考 60008错误详解");
        put("InternalError.UnknownError", "未知错误类型");
        put("InvalidParameter.AppidAndBizId", "账号与应用id不匹配");
        put("InvalidParameter.InvalidParameters", "International 或者 SmsType 参数有误，如有需要请联系 sms helper");
        put("InvalidParameterValue.ContentLengthLimit", "请求的短信内容太长，短信长度规则请参考 国内短信内容长度计算规则");
        put("InvalidParameterValue.ImageInvalid", "上传的转码图片格式错误，请参照 API 接口说明中对改字段的说明，如有需要请联系 sms helper");
        put("InvalidParameterValue.IncorrectPhoneNumber", "手机号格式错误");
        put("InvalidParameterValue.InvalidDocumentType", "DocumentType 字段校验错误，请参照 API 接口说明中对改字段的说明，如有需要请联系 sms helper");
        put("InvalidParameterValue.InvalidInternational	International", "字段校验错误，请参照 API 接口说明中对改字段的说明，如有需要请联系 sms helper");
        put("InvalidParameterValue.InvalidStartTime", "无效的拉取起始/截止时间，具体原因可能是请求的 SendDateTime 大于 EndDateTime");
        put("InvalidParameterValue.InvalidUsedMethod", "UsedMethod 字段校验错误，请参照 API 接口说明中对改字段的说明，如有需要请联系 sms helper");
        put("InvalidParameterValue.MissingSignatureList", "无法识别签名，请确认是否已有签名通过申请，一般是签名未通过申请，可以查看 签名审核 ");
        put("InvalidParameterValue.ProhibitedUseUrlInTemplateParameter", "禁止在模板变量中使用 URL");
        put("InvalidParameterValue.SdkAppidNotExist", "SdkAppid 不存在");
        put("InvalidParameterValue.SignAlreadyPassedCheck", "此签名已经通过审核，无法再次进行修改");
        put("InvalidParameterValue.TemplateParameterFormatError", "验证码模板参数格式错误，验证码类模版，模版变量只能传入0 - 6位（包括6位）纯数字");
        put("InvalidParameterValue.TemplateParameterLengthLimit", "单个模板变量字符数超过12个，企业认证用户不限制单个变量值字数，您可以 变更实名认证模式，变更为企业认证用户后，该限制变更约1小时左右生效");
        put("LimitExceeded.AppDailyLimit", "业务短信日下发条数超过设定的上限 ，可自行到控制台调整短信频率限制策略");
        put("LimitExceeded.DailyLimit", "短信日下发条数超过设定的上限 (国际/港澳台);，如需调整限制，可联系 sms helper");
        put("LimitExceeded.DeliveryFrequencyLimit", "下发短信命中了频率限制策略，可自行到控制台调整短信频率限制策略，如有其他需求请联系 sms helper");
        put("LimitExceeded.PhoneNumberCountLimit", "调用短信发送 API 接口单次提交的手机号个数超过200个，请遵守 API 接口说明");
        put("LimitExceeded.PhoneNumberDailyLimit", "单个手机号日下发短信条数超过设定的上限，可自行到控制台调整短信频率限制策略");
        put("LimitExceeded.PhoneNumberOneHourLimit", "单个手机号1小时内下发短信条数超过设定的上限，可自行到控制台调整短信频率限制策略");
        put("LimitExceeded.PhoneNumberSameContentDailyLimit", "单个手机号下发相同内容超过设定的上限，可自行到控制台调整短信频率限制策略");
        put("LimitExceeded.PhoneNumberThirtySecondLimit", "单个手机号30秒内下发短信条数超过设定的上限，可自行到控制台调整短信频率限制策略");
        put("MissingParameter.EmptyPhoneNumberSet", "传入的号码列表为空，请确认您的参数中是否传入号码");
        put("UnauthorizedOperation.IndividualUserMarketingSmsPermissionDeny", "个人用户没有发营销短信的权限，请参考 权益区别");
        put("UnauthorizedOperation.RequestIpNotInWhitelist", "请求 IP 不在白名单中，您配置了校验请求来源 IP，但是检测到当前请求 IP 不在配置列表中，如有需要请联系 sms helper");
        put("UnauthorizedOperation.RequestPermissionDeny", "请求没有权限，请联系 sms helper");
        put("UnauthorizedOperation.SdkAppidIsDisabled", "此 sdkappid 禁止提供服务，如有需要请联系 sms helper");
        put("UnauthorizedOperation.SerivceSuspendDueToArrears", "欠费被停止服务，可自行登录腾讯云充值来缴清欠款");
        put("UnauthorizedOperation.SmsSdkAppidVerifyFail", "SmsSdkAppid 校验失败");
        put("UnsupportedOperation.", "不支持该请求");
        put("UnsupportedOperation.ContainDomesticAndInternationalPhoneNumber", "群发请求里既有国内手机号也有国际手机号");
        put("UnsupportedOperation.UnsuportedRegion", "不支持该地区短信下发");
    }};

}
