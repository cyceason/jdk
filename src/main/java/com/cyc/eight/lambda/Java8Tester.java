package com.cyc.eight.lambda;

/**
 * lambda表达式, 语法 ： 1. (params) -> expression    2. (params) -> statement    3. (params) -> { statements }
 * params 代表接口唯一方法中的入参；expression 代表接口唯一方法中的方法体
 * 特性
 * 1. 可选类型声明 - 无需声明参数的类型。编译器可以从该参数的值推断
 * 2. 可选圆括号参数 - 无需在括号中声明参数。对于多个参数，括号是必需的。
 * 3. 可选大括号 - 表达式主体没有必要使用大括号，如果主体中含有一个单独的语句。
 * 4. 可选return关键字 - 编译器会自动返回值，如果主体有一个表达式返回的值。花括号是必需的，以表明表达式返回一个值。
 * <p>
 * Created by cyc_e on 2018/5/1.
 */
public class Java8Tester {
    public static void main(String[] args) {
        Java8Tester tester = new Java8Tester();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("FaceBook");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}