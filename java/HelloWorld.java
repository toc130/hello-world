import java.util.*;

public class HelloWorld {
    // 1. 类和对象示例
    static class Student {
        private String name;
        private int age;
        private int score;

        public Student(String name, int age, int score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public String introduce() {
            return String.format("我叫%s，今年%d岁，考试分数是%d分", name, age, score);
        }

        public String study(String subject) {
            return String.format("%s正在学习%s", name, subject);
        }
    }

    // 4. 函数定义和使用
    public static double calculateBMI(double weight, double height) {
        double bmi = weight / (height * height);
        return Math.round(bmi * 100.0) / 100.0;
    }

    public static String getBMIStatus(double bmi) {
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

    public static void main(String[] args) {
        System.out.println("\n1. 基本数据类型和变量示例：");
        // 整数
        int age = 25;
        // 浮点数
        double height = 1.75;
        // 字符串
        String name = "张三";
        // 布尔值
        boolean isStudent = true;
        // 数组
        String[] hobbies = {"读书", "游泳", "编程"};
        // Map（类似Python的字典）
        Map<String, Object> person = new HashMap<>();
        person.put("name", "张三");
        person.put("age", 25);
        person.put("city", "北京");

        System.out.printf("姓名：%s, 年龄：%d, 身高：%.2f米%n", name, age, height);
        System.out.println("是否是学生：" + isStudent);
        System.out.println("爱好：" + Arrays.toString(hobbies));
        System.out.println("个人信息：" + person);

        // 2. 条件控制
        System.out.println("\n2. 条件控制示例：");
        int score = 85;
        String grade;

        if (score >= 90) {
            grade = "优秀";
        } else if (score >= 80) {
            grade = "良好";
        } else if (score >= 60) {
            grade = "及格";
        } else {
            grade = "不及格";
        }

        System.out.printf("分数：%d, 等级：%s%n", score, grade);

        // 3. 循环
        System.out.println("\n3. 循环示例：");
        System.out.println("for循环遍历数组：");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        System.out.println("\nwhile循环计数：");
        int count = 0;
        while (count < 3) {
            System.out.println("计数：" + count);
            count++;
        }

        // 4. 函数使用
        System.out.println("\n4. 函数示例：");
        double weight = 70;
        double bmi = calculateBMI(weight, height);
        String status = getBMIStatus(bmi);
        System.out.printf("身高：%.2f米，体重：%.1f公斤%n", height, weight);
        System.out.printf("BMI：%.2f，身体状况：%s%n", bmi, status);

        // 5. 类和对象
        System.out.println("\n5. 类和对象示例：");
        Student student = new Student("李四", 18, 95);
        System.out.println(student.introduce());
        System.out.println(student.study("Java编程"));

        System.out.println("Hello, World!");
    }
}
