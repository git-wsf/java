package com.yangliuxin.advice;

import com.yangliuxin.vo.ResultVo;
import com.yangliuxin.enums.WebCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionAdvisor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvisor.class);

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
            resultVo.setMsg("Exception: " + error.getMessage());
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
        resultVo.setMsg("RuntimeException: " + error.getMessage());

        return resultVo;
    }
}