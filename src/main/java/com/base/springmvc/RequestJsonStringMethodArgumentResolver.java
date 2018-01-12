package com.base.springmvc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.base.springmvc.annotations.RequestJsonString;
import com.base.springmvc.exception.ServiceException;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Will WM. Zhang
 */
public class RequestJsonStringMethodArgumentResolver extends
        RequestParamMethodArgumentResolver {

    private static ObjectMapper objectMapper;
    private static TypeFactory typeFactory;
    private static Validator validator;

    static {
        objectMapper = new ObjectMapper();
        typeFactory = objectMapper.getTypeFactory();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public RequestJsonStringMethodArgumentResolver() {
        super(true);// true或者false有什么区别？
    }

    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJsonString.class);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected Object resolveName(String name, MethodParameter parameter,
                                 NativeWebRequest webRequest) throws Exception {

        String[] paramValues = webRequest.getParameterValues(name);
        if (paramValues == null) return null;
        Class<?> paramType = parameter.getParameterType();

        if (paramValues.length == 1) {
            String text = paramValues[0];

            if (WapperMap.class.isAssignableFrom(paramType)) {
                WapperMap<?, ?> wapperMap = (WapperMap<?, ?>) paramType.newInstance();
                wapperMap.setInnerMap(objectMapper.<Map>readValue(text, new TypeReference<Map>() {}));
                return wapperMap;
            }

            Type type = parameter.getGenericParameterType();
            JavaType javaType = typeFactory.constructType(type);
            Object object = objectMapper.readValue(text, javaType);

            if (parameter.hasParameterAnnotation(Valid.class)) {
                if (Collection.class.isAssignableFrom(paramType)) {
                    Collection collection = (Collection) object;
                    int i = 0;
                    for (Object obj : collection) {
                        validate(obj, "[" + i + "]");
                        i++;
                    }
                } else {
                    validate(object, "");
                }
            }

            return object;
        }
        throw new UnsupportedOperationException("too many request json parameter '" + name +
                "' for method parameter type [" + paramType + "], only support one json parameter");
    }

    private void validate(Object object, String index) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        for (ConstraintViolation<Object> vi : constraintViolations) {
            String message = vi.getMessage();
            String key = vi.getPropertyPath().iterator().next().getName();
            resultMap.put(key + index, message);
        }
        if (resultMap.size() > 0)
            throw new ServiceException(1, "校验不通过", resultMap);
    }

}