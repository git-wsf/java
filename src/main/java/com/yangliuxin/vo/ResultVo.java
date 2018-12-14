package com.yangliuxin.vo;

import com.yangliuxin.enums.WebCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.PrintWriter;
import java.io.StringWriter;

@ApiModel
public class ResultVo<T> {
    @ApiModelProperty("返回状态码 0 成功，-1失败")
    private Integer code;
    @ApiModelProperty("返回数据")
    private T data;
    @ApiModelProperty("返回信息")
    private String msg;
    @ApiModelProperty("异常信息")
    private String errorMsg;

    public static <T> ResultVo<T> success() {
        return success((T)null);
    }

    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setData(data);
        resultVo.setMsg("SUCCESS");
        return resultVo;
    }

    public static <T> ResultVo<T> error(String message) {
        ResultVo<T> resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg(message);
        resultVo.setErrorMsg(message);
        return resultVo;
    }

    public static <T> ResultVo<T> error(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        ResultVo<T> resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setErrorMsg(sw.getBuffer().toString());
        return resultVo;
    }

    public ResultVo<T> successNoData() {
        this.code = WebCodeEnum.SUCCESS.getValue();
        this.msg = "success";
        return this;
    }

    public ResultVo<T> LackParam() {
        this.code = WebCodeEnum.ERROR.getValue();
        this.msg = "LACK_REQUEST_PARAM!";
        return this;
    }

    public ResultVo() {
    }

    public Integer getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResultVo)) {
            return false;
        } else {
            ResultVo<?> other = (ResultVo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label59;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label59;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$errorMsg = this.getErrorMsg();
                Object other$errorMsg = other.getErrorMsg();
                if (this$errorMsg == null) {
                    if (other$errorMsg != null) {
                        return false;
                    }
                } else if (!this$errorMsg.equals(other$errorMsg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ResultVo;
    }

    public int hashCode() {
        //int PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $errorMsg = this.getErrorMsg();
        result = result * 59 + ($errorMsg == null ? 43 : $errorMsg.hashCode());
        return result;
    }

    public String toString() {
        return "ResultVo(code=" + this.getCode() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ", errorMsg=" + this.getErrorMsg() + ")";
    }
}
