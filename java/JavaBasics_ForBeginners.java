// 导入Java工具包，HashMap和Arrays等常用的类都在这里
import java.util.*;

/**
 * Java入门基础 (JavaBasics_ForBeginners)
 *
 * 欢迎Java初学者！这个文件将带你了解Java最基本也是最重要的概念。
 * 学习编程就像学习一门新语言，需要耐心和练习。
 * 不要害怕犯错，每个错误都是学习的机会！
 * 建议：
 * 1. 仔细阅读每一行代码和注释。
 * 2. 尝试修改代码中的值，看看会发生什么。
 * 3. 亲自动手编写类似的代码。
 * 4. 如果遇到不理解的地方，可以查阅资料或提问。
 *
 * 让我们开始Java的奇妙之旅吧！
 */
public class JavaBasics_ForBeginners {

    // main方法是Java程序的入口点，程序从这里开始执行。
    public static void main(String[] args) {

        System.out.println("你好，Java世界！让我们开始学习基础知识。");

        // ===================================================================
        // 1. 基本数据类型和变量 (Basic Data Types and Variables)
        // ===================================================================
        System.out.println("
--- 1. 基本数据类型和变量 ---");
        // 变量是用来存储数据的容器。在使用变量之前，需要声明它的类型和名称。

        // 示例1.1：整数类型 (Integer Types)
        // byte: 8位，范围 -128 到 127
        byte smallNumber = 100;
        System.out.println("这是一个byte类型的数字: " + smallNumber);

        // short: 16位，范围 -32,768 到 32,767
        short mediumNumber = 30000;
        System.out.println("这是一个short类型的数字: " + mediumNumber);

        // int: 32位，非常常用的整数类型，范围约 -20亿 到 20亿
        int age = 28; // 声明一个名为age的整型变量，并赋值为28
        System.out.println("年龄 (int): " + age);

        int population = 780000000; // 例如：城市人口
        System.out.println("城市人口 (int): " + population);

        // long: 64位，用于存储非常大的整数，需要在数字末尾加上'L'或'l'
        long worldPopulation = 7800000000L;
        System.out.println("世界人口 (long): " + worldPopulation);

        // 示例1.2：浮点类型 (Floating-Point Types) - 用于存储带小数的数字
        // float: 32位单精度浮点数，需要在数字末尾加上'F'或'f'
        float price = 19.99F;
        System.out.println("价格 (float): " + price + "元");

        // double: 64位双精度浮点数，更精确，是默认的小数类型
        double piValue = 3.1415926535;
        System.out.println("圆周率PI (double): " + piValue);

        double height = 1.78; // 例如：身高1.78米
        System.out.println("身高 (double): " + height + "米");

        // 示例1.3：字符类型 (Character Type)
        // char: 16位Unicode字符，用单引号括起来
        char gradeLetter = 'A';
        System.out.println("等级 (char): " + gradeLetter);

        char currencySymbol = '¥';
        System.out.println("货币符号 (char): " + currencySymbol);

        // 示例1.4：布尔类型 (Boolean Type) - 只有两个值：true 或 false
        boolean isJavaFun = true;
        System.out.println("Java有趣吗？ (boolean): " + isJavaFun);

        boolean hasPassedExam = false;
        System.out.println("考试通过了吗？ (boolean): " + hasPassedExam);

        // 示例1.5：变量的命名规则
        // - 可以包含字母、数字、下划线(_)和美元符号($)
        // - 必须以字母、下划线或美元符号开头
        // - 大小写敏感 (e.g., myVariable 和 myvariable 是不同的)
        // - 不能是Java的关键字 (e.g., int, class, public)
        // - 推荐使用驼峰命名法 (e.g., myVariableName, studentAge)
        String studentName = "王小明"; // 字符串类型，稍后会详细介绍
        System.out.println("学生姓名 (String): " + studentName);

        // 尝试不好的命名（仅为演示，不推荐）
        // int 123go = 5; // 编译错误：不能以数字开头
        // int class = 10; // 编译错误：不能使用关键字

        System.out.println("------------------------------");

        // ===================================================================
        // 2. 字符串 (Strings)
        // ===================================================================
        System.out.println("
--- 2. 字符串 ---");
        // String 不是基本数据类型，而是一个类 (Class)，但它非常常用。
        // 字符串用双引号括起来。

        String greeting = "你好, Java!";
        System.out.println("问候语: " + greeting);

        String city = "北京";
        String country = "中国";
        String location = city + ", " + country; // 字符串拼接
        System.out.println("地点: " + location);

        // 字符串常用方法示例
        String message = "   学习Java编程很有趣!   ";
        System.out.println("原始消息: '" + message + "'");
        System.out.println("消息长度: " + message.length()); // 获取长度
        System.out.println("转换为大写: " + message.toUpperCase()); // 转大写
        System.out.println("转换为小写: " + message.toLowerCase()); // 转小写
        System.out.println("去除首尾空格: '" + message.trim() + "'"); // 去除首尾空格
        System.out.println("是否包含 'Java': " + message.contains("Java")); // 是否包含子串
        System.out.println("从索引7开始的子串: " + message.trim().substring(7)); // 获取子串

        System.out.println("------------------------------");

        // ===================================================================
        // 3. 运算符 (Operators)
        // ===================================================================
        System.out.println("
--- 3. 运算符 ---");
        // 运算符是用于对变量和值执行操作的特殊符号。

        // 示例 3.1: 算术运算符 (Arithmetic Operators)
        System.out.println("
~~~ 3.1 算术运算符 ~~~");
        int numA = 10;
        int numB = 4;
        System.out.println(numA + " + " + numB + " = " + (numA + numB)); // 加法
        System.out.println(numA + " - " + numB + " = " + (numA - numB)); // 减法
        System.out.println(numA + " * " + numB + " = " + (numA * numB)); // 乘法
        System.out.println(numA + " / " + numB + " = " + (numA / numB)); // 除法 (整数除法，结果为整数)
        System.out.println(numA + " % " + numB + " = " + (numA % numB)); // 取模 (获取余数)

        double doubleA = 10.0;
        double doubleB = 4.0;
        System.out.println(doubleA + " / " + doubleB + " (浮点数除法) = " + (doubleA / doubleB));

        // 示例 3.1.1: 算术运算符与赋值结合
        int counter = 5;
        counter++; // 自增1 (等同于 counter = counter + 1)
        System.out.println("counter++ : " + counter);
        counter--; // 自减1 (等同于 counter = counter - 1)
        System.out.println("counter-- : " + counter);

        int sum = 10;
        sum += 5; // 等同于 sum = sum + 5;
        System.out.println("sum += 5 : " + sum);
        sum *= 2; // 等同于 sum = sum * 2;
        System.out.println("sum *= 2 : " + sum);

        // 示例 3.2: 关系运算符 (Relational Operators) - 结果总是布尔值 (true/false)
        System.out.println("
~~~ 3.2 关系运算符 ~~~");
        int val1 = 7;
        int val2 = 10;
        System.out.println(val1 + " == " + val2 + " : " + (val1 == val2)); // 等于
        System.out.println(val1 + " != " + val2 + " : " + (val1 != val2)); // 不等于
        System.out.println(val1 + " > " + val2 + " : " + (val1 > val2));  // 大于
        System.out.println(val1 + " < " + val2 + " : " + (val1 < val2));  // 小于
        System.out.println(val1 + " >= " + val2 + " : " + (val1 >= val2)); // 大于等于
        System.out.println(val1 + " <= " + val2 + " : " + (val1 <= val2)); // 小于等于

        // 示例 3.3: 逻辑运算符 (Logical Operators) - 用于连接多个条件
        System.out.println("
~~~ 3.3 逻辑运算符 ~~~");
        boolean condition1 = true;
        boolean condition2 = false;
        System.out.println("condition1 ("+condition1+") && condition2 ("+condition2+") : " + (condition1 && condition2)); // 逻辑与 (AND)
        System.out.println("condition1 ("+condition1+") || condition2 ("+condition2+") : " + (condition1 || condition2)); // 逻辑或 (OR)
        System.out.println("!condition1 ("+condition1+") : " + (!condition1)); // 逻辑非 (NOT)

        int studentAgeForLogic = 19;
        boolean hasStudentId = true;
        // 场景：判断是否为符合优惠条件的学生 (年龄小于22岁且持有学生证)
        boolean isEligibleStudent = (studentAgeForLogic < 22) && hasStudentId;
        System.out.println("年龄 " + studentAgeForLogic + ", 持有学生证 " + hasStudentId + " -> 符合优惠条件吗? " + isEligibleStudent);

        boolean isWeekend = false;
        boolean hasTime = true;
        // 场景：判断是否可以去公园 (是周末 或 有时间)
        boolean canGoToPark = isWeekend || hasTime;
        System.out.println("是周末 " + isWeekend + ", 有时间 " + hasTime + " -> 可以去公园吗? " + canGoToPark);


        // 示例 3.4: 赋值运算符 (Assignment Operators)
        System.out.println("
~~~ 3.4 赋值运算符 ~~~");
        int x = 10; // 基本赋值
        System.out.println("x 的初始值: " + x);
        x += 5; // x = x + 5
        System.out.println("x += 5 : " + x);
        x -= 3; // x = x - 3
        System.out.println("x -= 3 : " + x);
        x *= 2; // x = x * 2
        System.out.println("x *= 2 : " + x);
        x /= 4; // x = x / 4
        System.out.println("x /= 4 : " + x);
        x %= 3; // x = x % 3
        System.out.println("x %= 3 : " + x);

        // 示例 3.5: 位运算符 (Bitwise Operators) - (可选，初学者可先跳过)
        // 位运算符直接对数字的二进制位进行操作。
        // int a = 5;  // 二进制: 0101
        // int b = 3;  // 二进制: 0011
        // System.out.println("a & b : " + (a & b)); // 按位与 (0001 -> 1)
        // System.out.println("a | b : " + (a | b)); // 按位或 (0111 -> 7)
        // System.out.println("a ^ b : " + (a ^ b)); // 按位异或 (0110 -> 6)
        // System.out.println("~a : " + (~a));    // 按位非 (取反)
        // System.out.println("a << 1 : " + (a << 1)); // 左移 (1010 -> 10)
        // System.out.println("a >> 1 : " + (a >> 1)); // 右移 (0010 -> 2)
        System.out.println("
~~~ 3.5 位运算符 (初学者可暂时跳过) ~~~");
        System.out.println("位运算符用于直接操作数字的二进制位，在特定场景下非常有用，但初学阶段可以先不深入。");

        System.out.println("------------------------------");

        // ===================================================================
        // 4. 控制流 (Control Flow)
        // ===================================================================
        System.out.println("
--- 4. 控制流 ---");
        // 控制流语句用于根据条件执行不同的代码块或重复执行代码块。

        // 示例 4.1: if-else if-else 语句 (Conditional Statements)
        System.out.println("
~~~ 4.1 if-else if-else 语句 ~~~");
        int testScore = 78;
        System.out.println("测试分数: " + testScore);

        if (testScore >= 90) {
            System.out.println("等级: 优秀 (A)");
        } else if (testScore >= 80) {
            System.out.println("等级: 良好 (B)");
        } else if (testScore >= 70) {
            System.out.println("等级: 中等 (C)");
        } else if (testScore >= 60) {
            System.out.println("等级: 及格 (D)");
        } else {
            System.out.println("等级: 不及格 (F)");
        }

        // 示例 4.1.1: 单独的 if 语句
        int temperature = 30;
        if (temperature > 28) {
            System.out.println("天气炎热，注意防暑！ (当前温度: " + temperature + "°C)");
        }

        // 示例 4.1.2: if-else 语句
        int userAge = 17;
        if (userAge >= 18) {
            System.out.println("用户年龄 " + userAge + ", 已成年。");
        } else {
            System.out.println("用户年龄 " + userAge + ", 未成年。");
        }

        // 示例 4.1.3: 嵌套 if 语句
        boolean isVipMember = true;
        double purchaseAmount = 120.0;
        double discount = 0.0;

        if (purchaseAmount >= 100.0) {
            if (isVipMember) {
                discount = 0.15; // VIP会员享受15%折扣
                System.out.println("VIP会员购物满100元，享受15%折扣！");
            } else {
                discount = 0.05; // 普通顾客购物满100元，享受5%折扣
                System.out.println("普通顾客购物满100元，享受5%折扣！");
            }
        } else {
            System.out.println("购物金额未满100元，暂无折扣。");
        }
        double finalPrice = purchaseAmount * (1 - discount);
        System.out.println("最终价格: " + finalPrice + "元");


        // 示例 4.2: switch 语句 (Switch Statement)
        System.out.println("
~~~ 4.2 switch 语句 ~~~");
        // switch 语句用于基于变量的不同值执行不同的代码块。

        // 示例 4.2.1: 基于数字的 switch
        int dayOfWeek = 3; // 假设1代表周一, 2代表周二, ...
        String dayName;
        switch (dayOfWeek) {
            case 1:
                dayName = "星期一";
                break; // break语句用于跳出switch块
            case 2:
                dayName = "星期二";
                break;
            case 3:
                dayName = "星期三";
                break;
            case 4:
                dayName = "星期四";
                break;
            case 5:
                dayName = "星期五";
                break;
            case 6:
                dayName = "星期六";
                break;
            case 7:
                dayName = "星期日";
                break;
            default: // 如果没有任何case匹配，则执行default块
                dayName = "无效的星期";
                break;
        }
        System.out.println("今天是: " + dayName);

        // 示例 4.2.2: 基于字符串的 switch (Java 7及以上版本支持)
        String fruitNameSwitch = "Apple"; // Renamed from 'fruit' to avoid conflict
        System.out.println("我选择的水果是: " + fruitNameSwitch);
        switch (fruitNameSwitch.toLowerCase()) { // 转换为小写以进行不区分大小写的比较
            case "apple":
                System.out.println("苹果是红色的（通常）。");
                break;
            case "banana":
                System.out.println("香蕉是黄色的。");
                break;
            case "orange":
                System.out.println("橙子是橙色的。");
                break;
            default:
                System.out.println("我不认识这种水果。");
        }

        // 示例 4.2.3: switch中的穿透 (fall-through) - 如果省略break
        int month = 2;
        System.out.println("当前月份（数字）: " + month);
        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("这个月有31天。");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("这个月有30天。");
                break;
            case 2:
                System.out.println("这个月是二月，通常有28天，闰年有29天。");
                break;
            default:
                System.out.println("无效的月份。");
        }


        // 示例 4.3: for 循环 (For Loop)
        System.out.println("
~~~ 4.3 for 循环 ~~~");
        // for循环用于重复执行一段代码固定次数。

        // 示例 4.3.1: 基本的 for 循环 (打印数字0到4)
        System.out.println("基本for循环 (0-4):");
        for (int i = 0; i < 5; i++) { // 初始化; 条件; 更新
            System.out.print(i + " ");
        }
        System.out.println();

        // 示例 4.3.2: for 循环计算1到10的和
        int sumOfNumbers = 0;
        for (int i = 1; i <= 10; i++) {
            sumOfNumbers += i;
        }
        System.out.println("1到10的和: " + sumOfNumbers);

        // 示例 4.3.3: 增强型 for 循环 (For-Each Loop) - 用于遍历数组或集合
        String[] colors = {"红色", "绿色", "蓝色", "黄色"};
        System.out.println("使用增强for循环遍历颜色数组:");
        for (String color : colors) {
            System.out.println(color);
        }

        // 示例 4.3.4: 嵌套 for 循环 (打印乘法表的一小部分)
        System.out.println("嵌套for循环 (乘法表部分):");
        for (int i = 1; i <= 3; i++) { // 外层循环
            for (int j = 1; j <= 3; j++) { // 内层循环
                System.out.printf("%d * %d = %d	", i, j, (i * j));
            }
            System.out.println(); //换行
        }


        // 示例 4.4: while 循环 (While Loop)
        System.out.println("
~~~ 4.4 while 循环 ~~~");
        // while循环在给定条件为真时重复执行代码块。

        // 示例 4.4.1: 基本的 while 循环 (计数到3)
        int whileCounter = 0;
        System.out.println("while循环 (计数到3):");
        while (whileCounter < 3) {
            System.out.println("计数: " + whileCounter);
            whileCounter++;
        }

        // 示例 4.4.2: while 循环模拟用户输入直到输入 "quit"
        // (这里用一个计数器模拟，实际场景会用Scanner)
        int attempts = 0;
        String userInput = "";
        System.out.println("模拟用户输入 (输入 'quit' 退出):");
        while (!userInput.equals("quit") && attempts < 5) { // 最多尝试5次
            attempts++;
            if (attempts == 3) userInput = "quit"; // 模拟第三次输入quit
            else userInput = "try" + attempts;
            System.out.println("用户输入: " + userInput);
        }
        System.out.println("循环结束，共尝试 " + attempts + " 次。");


        // 示例 4.5: do-while 循环 (Do-While Loop)
        System.out.println("
~~~ 4.5 do-while 循环 ~~~");
        // do-while循环与while循环类似，但它至少会执行一次代码块，因为条件在代码块之后检查。

        // 示例 4.5.1: 基本的 do-while 循环
        int doWhileCounter = 0;
        System.out.println("do-while循环 (至少执行一次):");
        do {
            System.out.println("do-while计数: " + doWhileCounter);
            doWhileCounter++;
        } while (doWhileCounter < 0); // 条件一开始就是false，但循环体仍然执行了一次

        doWhileCounter = 0; // 重置
        System.out.println("do-while循环 (计数到2):");
        do {
            System.out.println("do-while计数: " + doWhileCounter);
            doWhileCounter++;
        } while (doWhileCounter < 2);


        // 示例 4.6: break 和 continue 语句
        System.out.println("
~~~ 4.6 break 和 continue ~~~");

        // 示例 4.6.1: break 在 for 循环中的使用 (找到数字后停止)
        System.out.println("使用break在for循环中查找数字5:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println("找到了数字 5，停止循环！");
                break; // 跳出整个for循环
            }
            System.out.println("当前数字: " + i);
        }

        // 示例 4.6.2: continue 在 for 循环中的使用 (跳过偶数)
        System.out.println("使用continue在for循环中打印奇数 (0-9):");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) { // 如果是偶数
                continue; // 跳过本次循环的剩余部分，直接进入下一次迭代
            }
            System.out.println("奇数: " + i);
        }

        // 示例 4.6.3: break 在 while 循环中
        int breakWhileCount = 0;
        System.out.println("使用break在while循环中:");
        while(true) { // 理论上是无限循环
            System.out.println("breakWhileCount: " + breakWhileCount);
            breakWhileCount++;
            if (breakWhileCount > 3) {
                break; // 当count大于3时跳出
            }
        }

        System.out.println("------------------------------");

        // ===================================================================
        // 5. 数组 (Arrays)
        // ===================================================================
        System.out.println("
--- 5. 数组 ---");
        // 数组是用于存储固定大小的同类型元素的集合。

        // 示例 5.1: 一维数组的声明和初始化
        System.out.println("
~~~ 5.1 一维数组的声明和初始化 ~~~");

        // 方法一：声明并指定大小，然后逐个赋值
        int[] numbers = new int[5]; // 声明一个可以存储5个整数的数组
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;
        // numbers[5] = 60; // 这行会产生 ArrayIndexOutOfBoundsException 错误，因为索引从0到4

        System.out.println("numbers数组的第一个元素: " + numbers[0]);
        System.out.println("numbers数组的第三个元素: " + numbers[2]);

        // 方法二：声明的同时进行初始化 (常用)
        String[] fruitsArray = {"苹果", "香蕉", "橙子", "葡萄"};
        System.out.println("fruitsArray的第二个水果: " + fruitsArray[1]);

        double[] scoresArray = {98.5, 87.0, 93.5, 77.8};
        System.out.println("scoresArray的第一个分数: " + scoresArray[0]);

        // 示例 5.2: 遍历数组
        System.out.println("
~~~ 5.2 遍历数组 ~~~");
        System.out.println("遍历numbers数组 (使用基本for循环):");
        for (int i = 0; i < numbers.length; i++) { // .length 获取数组长度
            System.out.println("元素 " + i + ": " + numbers[i]);
        }

        System.out.println("遍历fruitsArray数组 (使用增强for循环):");
        for (String currentFruit : fruitsArray) { // Renamed from 'fruitName' to avoid conflict
            System.out.println(currentFruit);
        }

        // 示例 5.3: 数组的一些常用操作
        System.out.println("
~~~ 5.3 数组的常用操作 ~~~");
        // 示例: 查找数组中的最大值
        int[] examScores = {78, 92, 65, 99, 85};
        int maxScore = examScores[0]; // 假设第一个是最大值
        for (int i = 1; i < examScores.length; i++) {
            if (examScores[i] > maxScore) {
                maxScore = examScores[i];
            }
        }
        System.out.println("最高分数是: " + maxScore);

        // 示例: 数组排序 (使用Java内置的排序功能)
        System.out.println("排序前的examScores: " + Arrays.toString(examScores)); // Arrays.toString()方便打印数组内容
        Arrays.sort(examScores); // 对数组进行升序排序
        System.out.println("排序后的examScores: " + Arrays.toString(examScores));

        // 示例 5.4: 二维数组 (Multidimensional Arrays) - 数组的数组
        System.out.println("
~~~ 5.4 二维数组 ~~~");
        // 可以看作是一个表格，有行和列

        // 声明并初始化一个2行3列的二维数组
        int[][] matrix = {
            {1, 2, 3},  // 第一行
            {4, 5, 6}   // 第二行
        };

        System.out.println("matrix[0][0]: " + matrix[0][0]); // 第一行第一列
        System.out.println("matrix[1][2]: " + matrix[1][2]); // 第二行第三列

        System.out.println("遍历二维数组 matrix:");
        for (int i = 0; i < matrix.length; i++) { // matrix.length 是行数
            for (int j = 0; j < matrix[i].length; j++) { // matrix[i].length 是第i行的列数
                System.out.print(matrix[i][j] + "	");
            }
            System.out.println(); // 每打印完一行后换行
        }

        // 声明一个不规则的二维数组 (每行的列数可以不同)
        System.out.println("不规则二维数组:");
        int[][] irregularArray = {
            {1, 2},
            {3, 4, 5},
            {6}
        };
        for (int i = 0; i < irregularArray.length; i++) {
            for (int j = 0; j < irregularArray[i].length; j++) {
                System.out.print(irregularArray[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------");

        // ===================================================================
        // 6. 方法 (Methods / Functions)
        // ===================================================================
        System.out.println("
--- 6. 方法 ---");
        // 方法是一段可重复使用的代码块，用于执行特定的任务。
        // 方法可以接收输入参数，并可以返回值。

        // 示例 6.1: 调用我们自己定义的方法
        greetUser("张三"); // 调用下面的 greetUser 方法
        greetUser("李四");

        int resultSum = addNumbers(15, 25); // 调用 addNumbers 方法并接收返回值
        System.out.println("15 + 25 = " + resultSum);
        System.out.println("100 + 200 = " + addNumbers(100, 200));

        printInfo("王五", 30); // 调用 printInfo 方法

        String weatherToday = getWeather(); // 调用 getWeather 方法
        System.out.println("今天的天气: " + weatherToday);

        // 示例 6.2: 静态方法 vs 实例方法
        // 我们目前在 main 方法（本身是一个静态方法）中调用的所有自定义方法 (greetUser, addNumbers等)
        // 也必须是静态的 (static)，或者通过类的实例来调用。
        // 关于实例方法，将在面向对象编程部分详细介绍。
        System.out.println("
~~~ 6.2 静态方法说明 ~~~");
        System.out.println("当前我们定义和调用的方法都是静态(static)方法。");
        System.out.println("静态方法属于类本身，而不是类的某个特定实例。");
        System.out.println("可以直接通过类名调用静态方法 (如果不在同一个类中)。");
        System.out.println("例如：Math.sqrt(25) 就是调用Math类的静态sqrt方法。");
        System.out.println("Math.sqrt(25) = " + Math.sqrt(25));


        // 原 HelloWorld.java 中的 calculateBMI 和 getBMIStatus 方法示例
        // 我们将它们作为静态方法定义在这个类中，以便演示
        System.out.println("
~~~ 6.3 BMI计算示例 (原HelloWorld方法) ~~~");
        double personWeight = 70; // kg
        double personHeight = 1.75; // meters
        double bmi = calculateBMI(personWeight, personHeight);
        String bmiStatus = getBMIStatus(bmi);
        System.out.printf("身高: %.2f米, 体重: %.1f公斤%n", personHeight, personWeight);
        System.out.printf("BMI指数: %.2f, 身体状况: %s%n", bmi, bmiStatus);

        double anotherWeight = 55;
        double anotherHeight = 1.60;
        System.out.printf("BMI(%.1fkg, %.2fm): %.2f, 状况: %s%n",
            anotherWeight, anotherHeight,
            calculateBMI(anotherWeight, anotherHeight),
            getBMIStatus(calculateBMI(anotherWeight, anotherHeight))
        );


        System.out.println("------------------------------");
        System.out.println("
Java基础知识学习第一部分完成！接下来请学习面向对象编程概念。");

    } // main 方法结束

    // ===================================================================
    // 方法定义区域 (Method Definitions)
    // 这些方法被定义在 JavaBasics_ForBeginners 类的内部，main 方法的外部。
    // 注意：因为main方法是静态的 (static)，它只能直接调用其他静态方法。
    // ===================================================================

    // 示例 6.1.1: 无返回值，有参数的方法 (void method with parameters)
    public static void greetUser(String name) {
        System.out.println("你好, " + name + "! 欢迎学习Java。");
    }

    // 示例 6.1.2: 有返回值，有参数的方法 (method with return value and parameters)
    public static int addNumbers(int a, int b) {
        int sum = a + b;
        return sum; // 返回计算结果
    }

    // 示例 6.1.3: 无返回值，多个参数的方法
    public static void printInfo(String name, int age) {
        System.out.println("姓名: " + name + ", 年龄: " + age);
    }

    // 示例 6.1.4: 有返回值，无参数的方法
    public static String getWeather() {
        // 简单模拟，实际中可能会从网络或传感器获取
        String[] weathers = {"晴朗", "多云", "小雨", "雷阵雨"};
        int randomIndex = (int) (Math.random() * weathers.length); // 生成一个随机索引
        return weathers[randomIndex];
    }

    // 示例 6.3.1: 计算BMI指数的方法 (来自原HelloWorld.java)
    /**
     * 计算BMI (Body Mass Index) 指数
     * @param weight 体重 (公斤)
     * @param height 身高 (米)
     * @return BMI值，四舍五入到两位小数
     */
    public static double calculateBMI(double weight, double height) {
        if (height <= 0) { // 防止除以零错误
            System.out.println("身高必须大于0！");
            return 0.0;
        }
        double bmi = weight / (height * height);
        return Math.round(bmi * 100.0) / 100.0; // 保留两位小数
    }

    // 示例 6.3.2: 根据BMI获取健康状况的方法 (来自原HelloWorld.java)
    /**
     * 根据BMI值获取健康状况描述
     * @param bmi BMI指数
     * @return 健康状况字符串
     */
    public static String getBMIStatus(double bmi) {
        if (bmi <= 0) return "无效的BMI值";
        if (bmi < 18.5) {
            return "偏瘦";
        } else if (bmi < 24) {
            return "正常";
        } else if (bmi < 28) {
            return "偏胖";
        } else {
            return "肥胖";
        }
    }

} // JavaBasics_ForBeginners 类结束
