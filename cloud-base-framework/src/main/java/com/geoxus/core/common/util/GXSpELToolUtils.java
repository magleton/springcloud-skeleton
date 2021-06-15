package com.geoxus.core.common.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;
import java.util.Optional;

/**
 * @author <email>britton@126.com</email>
 */
public class GXSpELToolUtils {
    /**
     * 私有函数
     */
    private GXSpELToolUtils() {

    }

    /**
     * 计算SpEL的表达式
     *
     * @param data             数据
     * @param expressionString 表达式  #data['name']=='jack' and #data['flags']==true
     * @return boolean
     */
    public static boolean calculatorExpression(Map<String, Object> data, String expressionString) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("data", data);
        final Expression expression = parser.parseExpression(expressionString);
        return Optional.ofNullable(expression.getValue(context, boolean.class)).orElse(true);
    }
}
