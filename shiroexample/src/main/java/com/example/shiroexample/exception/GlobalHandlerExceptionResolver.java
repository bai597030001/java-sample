package com.example.shiroexample.exception;

/**
 * @program: com.glodon.taikeystone
 * @description: 异常处理视图解析
 * @author: baijd
 * @create: 2020-06-24 09:29
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;

@Slf4j
@Configuration
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        Exception e = new Exception();

        if (ex instanceof UndeclaredThrowableException) {
            e = (Exception) ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
        } else {
            e = ex;
        }
        e.printStackTrace();
        //这里可以根据不同异常引起的类做不同处理方式
        String exceptionName = ClassUtils.getShortName(e.getClass());
        ModelAndView mav = new ModelAndView();
        if (exceptionName.equals("MaxUploadSizeExceededException")) {
            log.error("GlobalHandlerExceptionResolver resolveException ===>" + exceptionName);
            mav.addObject("result", "发生异常，文件太大");
            mav.setViewName("error");
        }else {
            mav.addObject("result", "未知错误！");
            mav.setViewName("error");
        }
        return mav;
    }

}
