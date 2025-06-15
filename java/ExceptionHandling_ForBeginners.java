// java/ExceptionHandling_ForBeginners.java

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Java 异常处理入门 (ExceptionHandling_ForBeginners)
 *
 * 异常 (Exception) 是程序在执行期间发生的意外或错误情况，它会中断程序的正常流程。
 * 异常处理是一种机制，允许我们优雅地处理这些错误，而不是让程序突然崩溃。
 *
 * 学习建议：
 * 1. 理解 `try`, `catch`, `finally` 各自的作用。
 * 2. 熟悉常见的异常类型，并知道它们通常在什么情况下发生。
 * 3. 练习编写能够从异常中恢复或至少能给出友好提示的代码。
 */

// ===================================================================
// 1. 什么是异常 (What is an Exception)?
// ===================================================================
// - 异常是在程序运行时发生的一个事件，它中断了指令的正常执行流程。
// - Java中的错误和异常都继承自 Throwable 类。
//   - Error (错误): 通常是JVM自身发生的问题，如内存溢出 (OutOfMemoryError)、栈溢出 (StackOverflowError)。
//     程序通常无法恢复，不建议捕获。
//   - Exception (异常): 是程序本身可以处理的问题。又分为：
//     - 受查异常 (Checked Exceptions): 编译器会强制要求处理的异常 (try-catch 或 throws)。通常是外部因素导致，如文件找不到、网络问题。
//     - 非受查异常 (Unchecked Exceptions / RuntimeExceptions): 编译器不强制要求处理。通常是程序逻辑错误，如空指针、数组越界。


// ===================================================================
// 2. 为什么需要处理异常 (Why handle Exceptions)?
// ===================================================================
// - **防止程序崩溃**: 如果不处理异常，程序遇到错误时会立即终止。
// - **优雅地处理错误**: 可以给用户友好的错误提示，而不是一堆看不懂的错误信息。
// - **资源管理**: 确保在发生异常时也能正确释放占用的资源 (如文件、网络连接)。
// - **程序健壮性**: 使程序能够应对各种意外情况，提高稳定性和可靠性。


// ===================================================================
// 3. `try-catch` 代码块 (The try-catch block)
// ===================================================================
// - `try` 块: 包含可能会抛出异常的代码。
// - `catch` 块: 用于捕获并处理 `try` 块中抛出的特定类型的异常。
//   - 一个 `try` 块后面可以跟多个 `catch` 块，用于捕获不同类型的异常。
//   - `catch` 块的顺序很重要，应该将更具体的异常类型放在前面，更通用的异常类型（如 Exception）放在后面。
//   - 从 Java 7 开始，可以使用 "multi-catch" 在一个 `catch` 块中处理多种异常类型。

class ExceptionDemo {

    public void demonstrateTryCatch() {
        System.out.println("
--- 演示 try-catch ---");
        try {
            int result = 10 / 0; // 这会抛出 ArithmeticException
            System.out.println("结果是: " + result); // 这行不会执行
        } catch (ArithmeticException e) {
            // e 是一个 ArithmeticException 类型的对象，包含了异常的信息
            System.err.println("错误：发生了算术异常！" + e.getMessage());
            // e.printStackTrace(); // 打印详细的堆栈跟踪信息，常用于调试
        }
        System.out.println("try-catch演示结束，程序继续执行。");
    }

    public void demonstrateMultipleCatchBlocks() {
        System.out.println("
--- 演示多个 catch 块 ---");
        try {
            // 为了避免在自动化测试中需要用户输入，我们直接模拟数据
            // Scanner scanner = new Scanner(System.in);
            // System.out.print("请输入一个数字: ");
            // int number = scanner.nextInt(); // 可能抛出 InputMismatchException

            // 模拟InputMismatchException的情况
            // String simulatedInput = "abc";
            // int number = Integer.parseInt(simulatedInput); // 这会抛出NumberFormatException，但为了演示InputMismatch，假设有个自定义解析器

            int[] array = {1, 2, 3};
            System.out.println("数组元素 array[5]: " + array[5]); // 可能抛出 ArrayIndexOutOfBoundsException

        } catch (InputMismatchException e) { // 这个catch块可能不会被下面的模拟代码触发
            System.err.println("输入错误：请输入一个有效的整数！ " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("数组错误：数组索引越界！ " + e.getMessage());
        } catch (Exception e) { // Exception 是所有异常的父类，可以捕获任何未被前面catch块捕获的异常
            System.err.println("发生了未知错误！ " + e);
        }
        System.out.println("多个catch块演示结束。");
    }

    public void demonstrateMultiCatch() {
        System.out.println("
--- 演示 multi-catch (Java 7+) ---");
        try {
            String numberStr = "abc";
            int number = Integer.parseInt(numberStr); // 可能抛出 NumberFormatException

            String text = null;
            System.out.println("字符串长度: " + text.length()); // 可能抛出 NullPointerException

        } catch (NumberFormatException | NullPointerException e) { // 使用 | 分隔多个异常类型
            System.err.println("发生了数字格式化或空指针异常: " + e.toString());
        }
        System.out.println("multi-catch演示结束。");
    }
}

// ===================================================================
// 4. 常见异常示例 (Common Exception Examples)
// ===================================================================
class CommonExceptionsDemo {

    // 4.1 ArithmeticException: 算术异常 (如除以零)
    public void causeArithmeticException() {
        System.out.println("
--- 演示 ArithmeticException ---");
        try {
            int result = 100 / 0;
            System.out.println("结果: " + result);
        } catch (ArithmeticException e) {
            System.err.println("ArithmeticException: " + e.getMessage());
        }
    }

    // 4.2 ArrayIndexOutOfBoundsException: 数组索引越界
    public void causeArrayIndexOutOfBoundsException() {
        System.out.println("
--- 演示 ArrayIndexOutOfBoundsException ---");
        try {
            int[] numbers = {10, 20, 30};
            System.out.println("元素: " + numbers[3]); // 数组只有0, 1, 2三个索引
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }

    // 4.3 NullPointerException: 空指针异常
    public void causeNullPointerException() {
        System.out.println("
--- 演示 NullPointerException ---");
        try {
            String str = null;
            System.out.println("字符串长度: " + str.length()); // 对null调用方法
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: 尝试在null对象上调用方法。 " + e);
        }
    }

    // 4.4 NumberFormatException: 数字格式化异常
    public void causeNumberFormatException() {
        System.out.println("
--- 演示 NumberFormatException ---");
        try {
            String notANumber = "123x";
            int number = Integer.parseInt(notANumber); // "123x" 不能转换为整数
            System.out.println("转换后的数字: " + number);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException: " + e.getMessage());
        }
    }

    // 4.5 InputMismatchException: 输入不匹配异常 (使用Scanner时)
    public void causeInputMismatchException(boolean simulate) {
        System.out.println("
--- 演示 InputMismatchException ---");
        if (!simulate) {
            System.out.println("（跳过Scanner输入以进行自动化测试，如需手动测试请修改simulate为true）");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("请输入一个整数: ");
            int num = scanner.nextInt(); // 如果用户输入了非整数（如 "abc"）
            System.out.println("你输入的整数是: " + num);
        } catch (InputMismatchException e) {
            System.err.println("InputMismatchException: 输入的不是一个有效的整数。");
            scanner.next(); // 清除无效的输入，否则下次scanner调用可能继续出错
        } finally {
            // scanner.close(); // 关闭System.in关联的Scanner后，后续测试可能无法再从控制台读取，因此通常在程序末尾关闭
            System.out.println("(Scanner演示部分结束)");
        }
    }
}

// ===================================================================
// 5. `finally` 代码块 (The finally block)
// ===================================================================
// - `finally` 块中的代码无论是否发生异常，都会执行（除非JVM退出，如System.exit()）。
// - 通常用于释放资源，如关闭文件流、网络连接、数据库连接等，确保资源得到正确清理。
// - `finally` 块是可选的。`try` 块后面可以只有 `catch`，只有 `finally`，或者两者都有。

class FinallyDemo {
    public void demonstrateFinally(boolean simulate) {
        System.out.println("
--- 演示 finally ---");
        if (!simulate) {
            System.out.println("（跳过Scanner输入以进行自动化测试，如需手动测试请修改simulate为true）");
             // 即使跳过，也演示一下没有异常时finally的执行
            try {
                System.out.println("try 块执行 (无异常)。");
            } catch (Exception e) {
                System.err.println("不应发生的异常: " + e.getMessage());
            } finally {
                System.out.println("finally 块被执行了！(无异常情况)");
            }
            System.out.println("demonstrateFinally 方法结束 (无异常情况)。");
            return;
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.print("在finally演示中输入一个数字 (尝试输入非数字引发异常): ");
            int value = scanner.nextInt();
            System.out.println("输入的值: " + value);
            if (value == 0) return; // 即使有return, finally也会执行
        } catch (InputMismatchException e) {
            System.err.println("FinallyDemo中的输入错误: " + e.getMessage());
            if (scanner != null) scanner.next(); // 清理错误输入
        } finally {
            System.out.println("finally 块被执行了！");
            // if (scanner != null) {
            //     System.out.println("关闭 scanner (在 finally 中)");
            //     // scanner.close(); // 注意：关闭System.in关联的Scanner后，后续可能无法再从控制台读取
            // }
        }
        System.out.println("demonstrateFinally 方法结束。");
    }
}

// ===================================================================
// 6. `throws` 关键字 和 7. 受查与非受查异常
// ===================================================================
// - `throws` 关键字: 用于在方法签名中声明该方法可能会抛出的受查异常。
//   调用者要么捕获这些异常，要么也使用 `throws` 声明抛出。
// - 受查异常 (Checked Exceptions):
//   - 继承自 `Exception` 类但不是 `RuntimeException` 的子类。
//   - 编译器强制要求处理。例如 `IOException`, `FileNotFoundException`, `SQLException`。
//   - 通常表示程序外部的、可预见的错误情况。
// - 非受查异常 (Unchecked Exceptions / RuntimeExceptions):
//   - 继承自 `RuntimeException` 类。
//   - 编译器不强制要求处理。例如 `NullPointerException`, `ArrayIndexOutOfBoundsException`, `ArithmeticException`。
//   - 通常表示程序内部的逻辑错误。

class ThrowsDemo {
    // 这个方法可能会抛出 IOException (一个受查异常)
    // 因此必须在方法签名中使用 throws 声明，或者在方法内部 try-catch 处理
    public void readFile(String filePath) throws java.io.IOException {
        if (filePath == null || filePath.isEmpty()) {
            // IllegalArgumentException 是非受查的，可以不显式声明throws
            throw new IllegalArgumentException("文件路径不能为空！(非受查)");
        }
        if (!filePath.equals("myFile.txt")) {
            // IOException 是受查的，必须声明或捕获
            throw new java.io.IOException("模拟文件未找到！(受查)");
        }
        System.out.println("文件 " + filePath + " 读取成功 (模拟)。");
    }

    public void callReadFile() {
        System.out.println("
--- 演示 throws 和受查/非受查异常 ---");
        try {
            // readFile("myFile.txt"); // 这行会成功（模拟）
            readFile("anotherFile.txt"); // 这会抛出我们模拟的IOException
        } catch (java.io.IOException e) { // 捕获readFile声明的受查异常
            System.err.println("调用readFile时捕获到IO异常: " + e.getMessage());
        } catch (IllegalArgumentException e) { // 捕获readFile内部可能抛出的非受查异常
            System.err.println("调用readFile时捕获到参数异常: " + e.getMessage());
        }

        try {
            readFile(null); // 这会抛出IllegalArgumentException
        } catch (java.io.IOException e) {
            System.err.println("再次调用readFile时捕获到IO异常: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("再次调用readFile时捕获到参数异常: " + e.getMessage());
        }
    }
}

// ===================================================================
// 8. 创建自定义异常 (Creating Custom Exceptions)
// ===================================================================
// - 可以通过继承 `Exception` (创建受查异常) 或 `RuntimeException` (创建非受查异常) 来定义自己的异常类。
// - 自定义异常可以使代码更具可读性，并能更精确地表达特定的错误情况。

// 自定义一个受查异常
class MyCustomCheckedException extends Exception {
    public MyCustomCheckedException(String message) {
        super(message); // 调用父类Exception的构造方法
    }
}

// 自定义一个非受查异常
class MyCustomUncheckedException extends RuntimeException {
    public MyCustomUncheckedException(String message) {
        super(message);
    }
    public MyCustomUncheckedException(String message, Throwable cause) {
        super(message, cause); // 可以包装另一个异常
    }
}

class CustomExceptionDemo {
    public void checkAge(int age) throws MyCustomCheckedException {
        if (age < 0) {
            throw new MyCustomCheckedException("年龄不能为负数: " + age);
        }
        if (age < 18) {
            System.out.println("年龄 " + age + ": 未成年。");
            // 可以选择不抛异常，或者抛一个不同的自定义异常
            // throw new MyCustomUncheckedException("未成年用户禁止访问此区域。");
        } else {
            System.out.println("年龄 " + age + ": 已成年，欢迎！");
        }
    }

    public void processPayment(double amount) {
        if (amount <= 0) {
            throw new MyCustomUncheckedException("支付金额必须大于0，当前金额：" + amount);
        }
        System.out.println("处理支付金额：" + amount + "元... 支付成功！");
    }


    public void demonstrateCustomExceptions() {
        System.out.println("
--- 演示自定义异常 ---");
        try {
            checkAge(25);
            checkAge(-5); // 这会抛出 MyCustomCheckedException
        } catch (MyCustomCheckedException e) {
            System.err.println("捕获到自定义受查异常: " + e.getMessage());
        }

        System.out.println();
        try {
            processPayment(100.0);
            processPayment(0); // 这会抛出 MyCustomUncheckedException
        } catch (MyCustomUncheckedException e) {
            System.err.println("捕获到自定义非受查异常: " + e.getMessage());
        }
    }
}


public class ExceptionHandling_ForBeginners {
    public static void main(String[] args) {
        System.out.println("--- Java 异常处理 (Exception Handling) 演示 ---
");

        ExceptionDemo demo1 = new ExceptionDemo();
        demo1.demonstrateTryCatch();
        demo1.demonstrateMultipleCatchBlocks(); // 调整为不依赖用户输入
        demo1.demonstrateMultiCatch();

        CommonExceptionsDemo demo2 = new CommonExceptionsDemo();
        demo2.causeArithmeticException();
        demo2.causeArrayIndexOutOfBoundsException();
        demo2.causeNullPointerException();
        demo2.causeNumberFormatException();
        demo2.causeInputMismatchException(false); // 传入false以跳过实际Scanner输入

        FinallyDemo demo3 = new FinallyDemo();
        demo3.demonstrateFinally(false); // 传入false以跳过实际Scanner输入，但仍会演示finally

        ThrowsDemo demo4 = new ThrowsDemo();
        demo4.callReadFile();

        CustomExceptionDemo demo5 = new CustomExceptionDemo();
        demo5.demonstrateCustomExceptions();

        System.out.println("
--- 异常处理演示结束 ---");
        System.out.println("重要提示：在实际开发中，不要滥用异常处理，也不要捕获过于宽泛的Exception，除非确实需要。");
        System.out.println("良好的错误提示和日志记录对于调试和用户体验非常重要。");

        // 全局的Scanner关闭（如果之前有未关闭的System.in相关的Scanner）
        // 通常一个应用程序中，System.in对应的Scanner应该有一个统一的管理策略
        // 为避免影响其他可能的测试或后续操作，这里不显式关闭System.in
    }
}
