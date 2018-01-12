package com.base.springmvc.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.base.springmvc.views.json.JsonView.*;
import static com.base.springmvc.views.json.JsonView.RESULT;
import static com.base.springmvc.views.json.JsonView.RESULT_CODE;
import static com.base.springmvc.views.json.JsonView.RESULT_MSG;

/**
 * @author Will WM. Zhang
 * @since 2017-02-12 17:28
 */
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception exception) {
        ModelAndView modelAndView = new ModelAndView("jsonView");
        Map<String, Object> model = modelAndView.getModel();
        if (exception instanceof ServiceException) {
            ServiceException se = (ServiceException) exception;
            model.put(RESULT_CODE, se.getResultCode());
            model.put(RESULT_MSG, se.getResultMsg());
            model.put(RESULT, se.getResult());
        } else {
            model.put(RESULT_CODE, -1);
            model.put(RESULT_MSG, (exception.getMessage() == null ? "系统异常" : exception.getMessage()));
            model.put(RESULT, "[" + exception.getClass().getName() + "]");
        }
        return modelAndView;
    }

}
