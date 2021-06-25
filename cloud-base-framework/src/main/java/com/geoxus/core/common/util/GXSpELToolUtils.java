package com.geoxus.core.common.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.validation.constraints.NotNull;
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
     * @return T
     */
    public static <T> T calculateSpELExpression(Dict data, String expressionString, Class<T> beanClass) {
        return calculateSpELExpression(data, expressionString, beanClass, "data");
    }

    /**
     * 计算目标类的表达式
     * <pre>
     *     {@code
     *     Dict data = GXSpELToolUtils.calculateSpELExpression(entity , "test == 'world' ? {'username':'枫叶'} :{'kk' : 'jack'}" , Dict.class);
     *     String data = GXSpELToolUtils.calculateSpELExpression(entity , "test == 'world' ? '枫叶' :{'jack'" , Dict.class);
     *     }
     * </pre>
     *
     * @param targetObject     目标对象
     * @param expressionString 表达式
     * @param beanClazz        返回对象的类型
     * @return T
     */
    public static <T> T calculateSpELExpression(Object targetObject, String expressionString, Class<T> beanClazz) {
        if (Objects.isNull(targetObject)) {
            return GXCommonUtils.getClassDefaultValue(beanClazz);
        }
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(targetObject);
        return parser.parseExpression(expressionString).getValue(context, beanClazz);
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
     *  String value = assignmentSpELExpression(targetObj , Dict.create().set("name" , "枫叶思源").set("age" , 12) , "name" , String.class);
     *  // 如果目标对象没有对应的字段存在
     *  // 则会抛出异常信息,eg:
     *  // String value = assignmentSpELExpression(targetObj , Dict.create().set("name111" , "枫叶思源").set("age" , 12) , "name" , String.class);
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
     *   String retVal = registerFunctionSpELExpression(StringUtils.class, String.class, "concat", new Class[]{String.class, String.class, String.class}, "hello", "britton", "枫叶思源");
     *   }</pre>
     *
     * @param targetClass      提供函数的目标类
     * @param methodName       目标方法
     * @param clazz            返回值的类型
     * @param methodParamTypes 方法参数的类型
     * @param params           调用参数的类型(实参
     * @return T
     */
    public static <T> T registerFunctionSpELExpression(Class<?> targetClass, String methodName, Class<T> clazz, Class<?>[] methodParamTypes, Object... params) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("params", params);
        final Method method = ReflectUtil.getMethod(targetClass, methodName, methodParamTypes);
        if (Objects.isNull(method)) {
            return null;
        }
        context.registerFunction(methodName, method);
        final String format = CharSequenceUtil.format("#{}({})", methodName, parsePlaceholderParams(methodParamTypes, params));
        return parser.parseExpression(format).getValue(context, clazz);
    }

    /**
     * 调用指定bean中的方法
     *
     * <pre>
     *     {@code
     *     String s = GXSpELToolUtils.callBeanMethodSpELExpression(
     *     HelloService.class,
     *     "hello",
     *     String.class,
     *     new Class[]{String.class, int.class, TestEntity.class},
     *     "枫叶思源", 98 , testEntity);
     *     }
     * </pre>
     *
     * @param beanClazz        Bean的Class对象
     * @param methodName       方法名字
     * @param clazz            返回的类型
     * @param methodParamTypes 参数的类型
     * @param params           参数列表
     * @return T
     */
    public static <T> T callBeanMethodSpELExpression(Class<?> beanClazz, String methodName, Class<T> clazz, Class<?>[] methodParamTypes, Object... params) {
        final Object beanObj = GXSpringContextUtils.getBean(beanClazz);
        if (Objects.isNull(beanObj)) {
            return null;
        }
        final ExpressionParser expressionParser = new SpelExpressionParser();
        final StandardEvaluationContext context = new StandardEvaluationContext(beanObj);
        final String expressionString = CharSequenceUtil.format("{}({})", methodName, parseArgumentParams(context, methodParamTypes, params));
        return expressionParser.parseExpression(expressionString).getValue(context, clazz);
    }

    /**
     * 调用指定对象中的方法
     * <pre>
     *  {@code
     *    String s = GXSpELToolUtils.callTargetObjectMethodSpELExpression(
     *    GXSpringContextUtils.getBean(HelloService.class),
     *    "hello",
     *    String.class,
     *    new Class[]{String.class, int.class,TestEntity.class},
     *    "枫叶思源", 98 , testEntity);
     *  }
     * </pre>
     *
     * @param targetObject     目标对象
     * @param methodName       方法名字
     * @param clazz            返回的类型
     * @param methodParamTypes 参数的类型
     * @param params           参数列表
     * @return T
     */
    public static <T> T callTargetObjectMethodSpELExpression(@NotNull Object targetObject, String methodName, Class<T> clazz, Class<?>[] methodParamTypes, Object... params) {
        final ExpressionParser expressionParser = new SpelExpressionParser();
        final StandardEvaluationContext context = new StandardEvaluationContext(targetObject);
        final String expressionString = CharSequenceUtil.format("{}({})", methodName, parseArgumentParams(context, methodParamTypes, params));
        return expressionParser.parseExpression(expressionString).getValue(context, clazz);
    }

    /**
     * 解析可变参数占位符
     *
     * @param methodParamTypes 参数的类型
     * @param params           具体参数
     * @return StringBuilder
     */
    private static String parsePlaceholderParams(Class<?>[] methodParamTypes, Object... params) {
        StringBuilder methodParam = new StringBuilder();
        for (int i = 0; i < methodParamTypes.length; i++) {
            if (i >= params.length) {
                methodParam.append("null , ");
                continue;
            }
            methodParam.append("#params[").append(i).append("] , ");
        }
        return CharSequenceUtil.subBefore(methodParam.toString(), ',', true);
    }

    /**
     * 解析可变参数实际参数
     *
     * @param methodParamTypes 参数的类型
     * @param params           具体参数
     * @return StringBuilder
     */
    private static String parseArgumentParams(EvaluationContext context, Class<?>[] methodParamTypes, Object... params) {
        StringBuilder methodParam = new StringBuilder();
        for (int i = 0; i < methodParamTypes.length; i++) {
            if (i >= params.length) {
                methodParam.append("null , ");
                continue;
            }
            final Class<?> type = methodParamTypes[i];
            if (ClassUtil.isSimpleValueType(type)) {
                if (type.getSimpleName().equalsIgnoreCase("string")) {
                    methodParam.append("'").append(params[i]).append("' , ");
                } else {
                    methodParam.append(params[i]).append(" , ");
                }
            } else {
                final String name = CharSequenceUtil.replace(type.getName(), ".", "");
                context.setVariable(name, params[i]);
                methodParam.append("#").append(name).append(" , ");
            }
        }
        return CharSequenceUtil.subBefore(methodParam.toString(), ',', true);
    }
}
