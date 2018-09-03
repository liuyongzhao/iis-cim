package com.supcon.soft.iis.cim.core;

import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Global Exception Handler for Application
 * @author qiyuqi
 * @see <a href="https://www.cnblogs.com/nosqlcoco/p/5562107.html"/>
 * @see <a href="https://blog.csdn.net/VcStrong/article/details/70159062"/>*/


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    public String runtimeExceptionHandler(RuntimeException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InfoNotFoundException.class)


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String infoNotFoundException(InfoNotFoundException ex){
        return ex.getMessage();
    }
}
