package com.geoxus.core.common.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Objects;

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
     * <pre> {@code
     *  Dict data = Dict.create().set("name","jack").set("age",12);
     *  String expression = "#data['name']=='jack' and #data['flags']==true";
     *  calculateSpELExpression(data ,expression ,String.class, "data");
     * } </pre>
     *
     * @param data             数据
     * @param expressionString 表达式  #data['name']=='jack' and #data['flags']==true
     * @param beanClass        返回的数据类型  Dog.class
     * @param dataKey          数据key data1 那么expressionString #data1['name']=='jack' and #data1['flags']==true
     * @return T
     */
    public static <T> T calculateSpELExpression(Dict data, String expressionString, Class<T> beanClass, String dataKey) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        dataKey = Objects.isNull(dataKey) ? "data" : dataKey;
        context.setVariable(dataKey, data);
        final Expression expression = parser.parseExpression(expressionString);
        return expression.getValue(context, beanClass);
    }

    /**
     * 在计算时动态为目标对象设置值
     *
     * <pre> {@code
     *  public class Inventor{
     *      private String name;
     *      private int age;
     *      private int roleId;
     *  }
     *  Inventor targetObj = new Inventor();
     *  String value = assignmentSpELExpression( , Dict.create().set("name" , "枫叶思源").set("age" , 12) , "name" , String.class);
     *  // 如果目标对象没有对应的字段存在
     *  // 则会抛出异常信息,eg:
     *  // String value = assignmentSpELExpression( , Dict.create().set("name111" , "枫叶思源").set("age" , 12) , "name" , String.class);
     * }</pre>
     *
     * @param targetObj 目标对象
     * @param data      动态设置的值
     * @param targetKey 目标对象的key
     * @param clazz     返回的值的类型
     * @return T
     */
    public static <T> T assignmentSpELExpression(Object targetObj, Dict data, String targetKey, Class<T> clazz) {
        final StandardEvaluationContext inventorContext = new StandardEvaluationContext(targetObj);
        final ExpressionParser parser = new SpelExpressionParser();
        if (data.isEmpty()) {
            return null;
        }
        data.forEach((key, value) -> parser.parseExpression(key).setValue(inventorContext, value));
        return parser.parseExpression(targetKey).getValue(inventorContext, clazz);
    }

    /**
     * 动态注册函数并调用它进行计算
     *
     * <pre>{@code
     *   public class StringUtils {
     *       public static String concat(String str1, String str2, String str3) {
     *         return "链接字符串 : " + str1 + " ---- " + str2 + " ==== " + str3;
     *     }
     *   }
     *   String retVal = registerFunctionSpELExpression(StringUtils.class, "concat", new Class[]{String.class, String.class, String.class}, String.class, "hello", "britton", "枫叶思源");
     *   }</pre>
     *
     * @param targetClass      提供函数的目标类
     * @param methodName       目标方法
     * @param methodParamTypes 方法参数的类型
     * @param clazz            返回值的类型
     * @param params           调用参数的类型(实参
     * @return T
     */
    public static <T> T registerFunctionSpELExpression(Class<?> targetClass, String methodName, Class<?>[] methodParamTypes, Class<T> clazz, Object... params) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("params", params);
        final Method method = ReflectUtil.getMethod(targetClass, methodName, methodParamTypes);
        if (Objects.isNull(method)) {
            return null;
        }
        context.registerFunction(methodName, method);
        StringBuilder methodParam = new StringBuilder();
        for (int i = 0; i < methodParamTypes.length; i++) {
            if (i >= params.length) {
                methodParam.append("null , ");
                continue;
            }
            methodParam.append("#params[").append(i).append("] , ");
        }
        final String format = CharSequenceUtil.format("#{}({})", methodName, CharSequenceUtil.subBefore(methodParam.toString(), ',', true));
        return parser.parseExpression(format).getValue(context, clazz);
    }
}
