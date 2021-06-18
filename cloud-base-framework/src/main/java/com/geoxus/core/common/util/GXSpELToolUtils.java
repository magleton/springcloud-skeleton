package com.geoxus.core.common.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * SpEL工具类
 *
 * @author <email>britton@126.com</email>
 */
public class GXSpELToolUtils {
    /**
     * 私有函数
     * 防止被外部构造
     */
    private GXSpELToolUtils() {

    }

    /**
     * 计算SpEL的表达式
     *
     * @param data             数据
     * @param expressionString 表达式  #data['name']=='jack' and #data['flags']==true
     * @param beanClass        返回的数据类型  Dog.class
     * @param dataKey          数据key data1 那么expressionString #data1['name']=='jack' and #data1['flags']==true
     * @return T
     */
    public static <T> T calculateSpELExpression(Map<String, Object> data, String expressionString, Class<T> beanClass, String dataKey) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        dataKey = Objects.isNull(dataKey) ? "data" : dataKey;
        context.setVariable(dataKey, data);
        final Expression expression = parser.parseExpression(expressionString);
        final T expressionValue = expression.getValue(context, beanClass);
        return Optional.ofNullable(expressionValue).orElse(GXCommonUtils.getClassDefaultValue(beanClass));
    }
}
