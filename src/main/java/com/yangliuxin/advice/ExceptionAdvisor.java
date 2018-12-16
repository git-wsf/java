package com.yangliuxin.advice;

import com.yangliuxin.exceptions.WeChatAuthorizeException;
import com.yangliuxin.property.WeChatAccountProperty;
import com.yangliuxin.vo.ResultVo;
import com.yangliuxin.enums.WebCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionAdvisor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvisor.class);


    @Autowired
    private WeChatAccountProperty  weChatAccountProperty;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResultVo exceptionHandler(Exception error) {
        ResultVo<Object> resultVo = new ResultVo<>();
        LOGGER.error(error.getMessage(), error);

        resultVo.setCode(WebCodeEnum.ERROR.getValue());
        if (error instanceof MethodArgumentNotValidException) {
            List<String> errorMessage = new ArrayList<>();
            for (ObjectError objectError : ((MethodArgumentNotValidException) error).getBindingResult().getAllErrors()) {
                errorMessage.add(objectError.getDefaultMessage());
            }
            resultVo.setMsg(String.join(",",errorMessage));
        } else {
            resultVo.setMsg(error.getMessage());
        }

        return resultVo;
    }

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResultVo formatCheckExceptionHandler(RuntimeException error) {
        ResultVo<Object> resultVo = new ResultVo<>();
        LOGGER.error(error.getMessage(), error);

        resultVo.setCode(WebCodeEnum.ERROR.getValue());
        resultVo.setMsg(error.getMessage());

        return resultVo;
    }

    @ExceptionHandler(WeChatAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(WeChatAuthorizeException e){
        return new ModelAndView("redirect:"
                .concat(weChatAccountProperty.getSiteUrl())
                .concat("/wechat/authorize")
                .concat("?returnUrl=")
                .concat(weChatAccountProperty.getSiteUrl())
                .concat(e.getMessage()));
    }
}