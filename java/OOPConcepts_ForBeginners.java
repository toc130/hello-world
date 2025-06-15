// java/OOPConcepts_ForBeginners.java

import java.util.UUID; // For generating unique student IDs

/**
 * 面向对象编程入门 (OOPConcepts_ForBeginners)
 *
 * 欢迎来到面向对象编程 (Object-Oriented Programming, OOP) 的世界！
 * OOP是一种编程思想，它将现实世界中的事物抽象成程序中的“对象”，
 *并通过对象之间的协作来完成复杂的任务。
 *
 * 主要概念包括：类 (Class), 对象 (Object), 封装 (Encapsulation), 继承 (Inheritance), 多态 (Polymorphism)。
 *
 * 学习建议：
 * 1. 理解每个概念的定义和目的。
 * 2. 仔细观察示例代码是如何体现这些概念的。
 * 3. 尝试自己设计和编写简单的类。
 * 4. 将OOP思想与现实生活中的事物联系起来，有助于理解。
 */

// ===================================================================
// 0. 辅助类：Animal (用于后续继承和多态示例)
// ===================================================================
/**
 * Animal 类 (动物类) - 作为后续继承示例的基类 (父类)
 * 为了演示抽象，我们稍后会将其修改为抽象类。
 */
class Animal {
    String name; // 动物的名字

    // 构造方法
    public Animal(String name) {
        this.name = name;
        System.out.println("Animal '" + this.name + "' 的构造方法被调用。");
    }

    public void eat() {
        System.out.println(name + " 正在吃东西...");
    }

    public void sleep() {
        System.out.println(name + " 正在睡觉...");
    }

    // 一个通用的发出声音的方法，子类可以覆盖它
    public void makeSound() {
        System.out.println(name + " 发出了一些声音。");
    }

    // 为了演示抽象方法，我们先定义一个普通方法，稍后修改
    public void makeSoundAbstractly() {
        System.out.println(name + " (通过普通方法) 发出声音。");
    }
}

// ===================================================================
// 1. 类 (Class) 和 对象 (Object)
// ===================================================================
/**
 * 1.1 类 (Class)
 * 类是创建对象的蓝图或模板。它定义了一类事物共有的属性（特征）和行为（能做什么）。
 * 例如，“狗”是一个类，它有“名字”、“品种”、“年龄”等属性，以及“吠叫”、“摇尾巴”等行为。
 */

/**
 * 1.2 对象 (Object)
 * 对象是类的具体实例 (instance)。根据“狗”这个类，我们可以创建出很多只具体的狗，
 * 比如一只叫“旺财”的中华田园犬，3岁；一只叫“小白”的贵宾犬，2岁。
 * “旺财”和“小白”就是“狗”类的两个不同对象。
 */

// 示例 1.A: 定义一个简单的 Dog 类
class Dog extends Animal { // Dog 继承自 Animal
    // 属性 (Attributes / Fields / Instance Variables)
    // String name; // 从Animal类继承而来
    String breed; // 品种
    int age;    // 年龄

    // 构造方法 (Constructor) - 用于创建Dog对象时初始化属性
    // 构造方法名必须与类名相同，并且没有返回类型
    public Dog(String name, String breed, int age) {
        super(name); // 调用父类Animal的构造方法来初始化name
        this.breed = breed;
        this.age = age;
        System.out.println("Dog '" + this.name + "' 的构造方法被调用。");
    }

    // 方法 (Methods / Behaviors)
    public void bark() {
        System.out.println(name + " (一只" + breed + ") 正在汪汪叫！");
    }

    public void fetch(String item) {
        System.out.println(name + " 叼回了 " + item + "。");
    }

    public void displayInfo() {
        System.out.println("狗狗信息 -> 名字: " + name + ", 品种: " + breed + ", 年龄: " + age + "岁");
    }

    // 覆盖父类的makeSound方法 (多态)
    @Override // @Override 注解表示这个方法是覆盖父类的方法，有助于编译器检查
    public void makeSound() {
        System.out.println(name + " (狗狗) 正在汪汪叫！ (覆盖自Animal)");
    }

    // 实现抽象方法 (如果Animal是抽象类且makeSoundAbstractly是抽象方法)
    // @Override
    // public void makeSoundAbstractly() {
    //     System.out.println(name + " (狗狗) 正在汪汪叫！ (实现自Animal的抽象方法)");
    // }
}

// 示例 1.B: 定义一个简单的 Book 类
class Book {
    String title;
    String author;
    String isbn;
    private int pageCount; // 页数，设为私有以演示封装

    public Book(String title, String author, String isbn, int pageCount) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.setPageCount(pageCount); // 使用setter方法进行可能的校验
        System.out.println("Book '" + this.title + "' 的构造方法被调用。");
    }

    public void displayInfo() {
        System.out.println("书籍信息 -> 书名:《" + title + "》, 作者: " + author + ", ISBN: " + isbn + ", 页数: " + pageCount);
    }

    // Getter for pageCount
    public int getPageCount() {
        return pageCount;
    }

    // Setter for pageCount (可以加入校验逻辑)
    public void setPageCount(int pageCount) {
        if (pageCount > 0) {
            this.pageCount = pageCount;
        } else {
            System.out.println("错误：页数必须大于0。将页数设置为默认值1。");
            this.pageCount = 1; // 设置一个默认值
        }
    }
}


// 示例 1.C: Student 类 (源自原HelloWorld.java，并加以扩展)
class Student {
    // 属性
    private String studentId; // 学号，设为私有
    private String name;      // 姓名，设为私有
    private int age;          // 年龄，设为私有
    private double score;     // 分数，设为私有

    // 构造方法1: 带所有参数
    public Student(String name, int age, double score) {
        this.studentId = UUID.randomUUID().toString().substring(0, 8); // 自动生成一个唯一ID
        this.name = name;
        this.setAge(age); // 使用setter进行校验
        this.setScore(score); // 使用setter进行校验
        System.out.println("Student '" + this.name + "' (ID: " + this.studentId + ") 的构造方法被调用。");
    }

    // 构造方法2: 重载 (Overloading) - 只带姓名和年龄，分数默认为0
    public Student(String name, int age) {
        this(name, age, 0.0); // 调用上面的构造方法，传入默认分数0.0 (构造方法链)
        System.out.println("Student '" + this.name + "' (ID: " + this.studentId + ") 的重载构造方法被调用 (默认分数)。");
    }

    // Getter 和 Setter 方法 (用于实现封装)
    public String getStudentId() {
        return studentId;
    }
    // studentId 通常不提供setter，因为它是唯一标识符，一旦设定不应轻易改变

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("错误：学生姓名不能为空。");
        }
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if (age > 0 && age < 150) { // 简单校验
            this.age = age;
        } else {
            System.out.println("错误：年龄 " + age + " 无效。年龄将设置为默认值18。");
            this.age = 18; // 设置一个默认年龄
        }
    }

    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        if (score >= 0 && score <= 100) { // 简单校验
            this.score = score;
        } else {
            System.out.println("错误：分数 " + score + " 无效 (应在0-100之间)。分数将设置为0。");
            this.score = 0;
        }
    }

    // 其他方法
    public String introduce() {
        return String.format("大家好，我叫%s，学号是%s，今年%d岁，目前考试分数是%.1f分。", name, studentId, age, score);
    }

    public void study(String subject) {
        System.out.println(name + " 正在努力学习 " + subject + "...");
    }

    public String getGrade() {
        if (score >= 90) return "优秀 (A)";
        if (score >= 80) return "良好 (B)";
        if (score >= 70) return "中等 (C)";
        if (score >= 60) return "及格 (D)";
        return "不及格 (F)";
    }
}


// ===================================================================
// 2. 构造方法 (Constructors) - 已在上面的类定义中演示
// ===================================================================
// - 构造方法是一种特殊的方法，用于创建和初始化对象。
// - 方法名与类名完全相同。
// - 没有返回类型 (连void也没有)。
// - 如果不显式定义构造方法，Java会提供一个默认的无参构造方法。
// - 一旦定义了任何构造方法，默认的无参构造方法就不再自动提供。
// - 构造方法可以重载 (Overload)，即可以有多个同名但参数列表不同的构造方法。


// ===================================================================
// 3. 封装 (Encapsulation)
// ===================================================================
// - 封装是将数据（属性）和操作这些数据的方法（行为）捆绑到一个单元（即类）中。
// - 信息隐藏：通过使用访问修饰符（如 private），限制对类内部成员的直接访问。
// - 目的：保护数据不被随意修改，提高代码的安全性和可维护性。
// - 通常通过 public 的 getter 和 setter 方法来间接访问和修改私有属性。
//   (已在 Student 类和 Book 类的 pageCount 属性中演示)


// ===================================================================
// 4. 继承 (Inheritance)
// ===================================================================
// - 继承允许一个类（子类/派生类）获取另一个类（父类/基类）的属性和方法。
// - 实现代码复用，并建立类之间的 "is-a" (是一个) 关系。
// - 使用 `extends` 关键字来实现继承。
// - 子类可以拥有父类没有的额外属性和方法。
// - 子类可以覆盖 (Override) 父类的方法以提供特定的实现。
// - Java只支持单继承 (一个子类只能有一个直接父类)，但可以通过接口实现多重继承的效果。
// - `super` 关键字：用于从子类中访问父类的成员（属性、方法、构造方法）。
//   (已在 Dog 类继承 Animal 类中演示)

// 示例 4.A: Cat 类继承自 Animal
class Cat extends Animal {
    String furColor; // 毛发颜色 (Cat特有的属性)

    public Cat(String name, String furColor) {
        super(name); // 调用父类Animal的构造方法
        this.furColor = furColor;
        System.out.println("Cat '" + this.name + "' 的构造方法被调用。");
    }

    public void scratch() {
        System.out.println(name + " (一只" + furColor + "的猫) 正在抓沙发！");
    }

    // 覆盖父类的makeSound方法 (多态)
    @Override
    public void makeSound() {
        System.out.println(name + " (猫咪) 正在喵喵叫！ (覆盖自Animal)");
    }

    // 实现抽象方法 (如果Animal是抽象类且makeSoundAbstractly是抽象方法)
    // @Override
    // public void makeSoundAbstractly() {
    //     System.out.println(name + " (猫咪) 正在喵喵叫！ (实现自Animal的抽象方法)");
    // }
}


// ===================================================================
// 5. 多态 (Polymorphism)
// ===================================================================
// - 多态意味着“多种形态”。它允许我们以统一的方式处理不同类型的对象。
// - 主要通过两种方式实现：
//   1. 方法覆盖 (Method Overriding): 子类重新定义父类中已有的方法。
//      (已在 Dog 和 Cat 类中覆盖 Animal 的 makeSound 方法中演示)
//   2. 方法重载 (Method Overloading): 在同一个类中，方法名相同但参数列表不同。
//      (已在 Student 类的构造方法中演示)
// - 好处：提高代码的灵活性和可扩展性。

// 演示多态的核心：父类引用指向子类对象
// Animal myPet = new Dog("旺财", "中华田园犬", 3);
// myPet.makeSound(); // 会调用 Dog 类的 makeSound 方法

// Animal anotherPet = new Cat("咪咪", "橘色");
// anotherPet.makeSound(); // 会调用 Cat 类的 makeSound 方法


// ===================================================================
// 6. 抽象 (Abstraction) - 简化版
// ===================================================================
// - 抽象是隐藏复杂的实现细节，只向用户展示必要的功能。
// - 抽象类 (Abstract Class):
//   - 使用 `abstract` 关键字声明。
//   - 不能被实例化 (不能创建抽象类的对象)。
//   - 可以包含抽象方法和具体方法。
//   - 主要用作其他类的基类。
// - 抽象方法 (Abstract Method):
//   - 使用 `abstract` 关键字声明。
//   - 只有方法签名，没有方法体 (没有花括号和实现代码)。
//   - 包含抽象方法的类必须声明为抽象类。
//   - 子类继承抽象类后，必须实现父类中所有的抽象方法 (除非子类本身也是抽象类)。

// 我们现在将 Animal 类修改为抽象类，并添加一个抽象方法
abstract class AbstractAnimal { // 注意类名修改以避免与之前的Animal冲突
    String name;

    public AbstractAnimal(String name) {
        this.name = name;
    }

    public abstract void makeSoundAbstractly(); // 抽象方法，没有方法体

    public void eat() { // 具体方法
        System.out.println(name + " (抽象动物) 正在吃东西。");
    }
     public void sleep() {
        System.out.println(name + " (抽象动物) 正在睡觉。");
    }
}

class ConcreteDog extends AbstractAnimal {
    String breed;
    public ConcreteDog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    @Override
    public void makeSoundAbstractly() { // 实现父类的抽象方法
        System.out.println(name + " (狗狗) 汪汪汪！ (实现自AbstractAnimal的抽象方法)");
    }

    public void fetch() {
        System.out.println(name + " 正在捡球。");
    }
}

class ConcreteCat extends AbstractAnimal {
    String furColor;
    public ConcreteCat(String name, String furColor) {
        super(name);
        this.furColor = furColor;
    }

    @Override
    public void makeSoundAbstractly() { // 实现父类的抽象方法
        System.out.println(name + " (猫咪) 喵喵喵！ (实现自AbstractAnimal的抽象方法)");
    }

    public void scratch() {
        System.out.println(name + " 正在抓东西。");
    }
}


// 主程序入口，用于演示OOP概念
public class OOPConcepts_ForBeginners {
    public static void main(String[] args) {
        System.out.println("--- 面向对象编程 (OOP) 概念演示 ---
");

        // --- 1. 类和对象演示 ---
        System.out.println("--- 1. 类和对象 ---");
        // 创建 Dog 对象
        Dog myDog = new Dog("旺财", "中华田园犬", 3);
        myDog.displayInfo();
        myDog.bark();
        myDog.fetch("球");
        myDog.eat(); // 来自父类 Animal
        myDog.sleep(); // 来自父类 Animal

        System.out.println();
        Dog anotherDog = new Dog("小白", "贵宾犬", 2);
        anotherDog.displayInfo();
        anotherDog.bark();

        System.out.println("
--- 创建 Book 对象 ---");
        Book javaBook = new Book("Java核心技术卷一", "Cay S. Horstmann", "978-7-111-57566-0", 800);
        javaBook.displayInfo();
        Book pythonBook = new Book("Python编程从入门到实践", "Eric Matthes", "978-7-115-42802-8", 0); // 页数给个无效的
        pythonBook.displayInfo();
        pythonBook.setPageCount(550); // 通过setter修改
        pythonBook.displayInfo();


        System.out.println("
--- 创建 Student 对象 (演示构造方法重载和封装) ---");
        Student student1 = new Student("张三", 20, 88.5);
        System.out.println(student1.introduce());
        System.out.println(student1.getName() + "的成绩等级: " + student1.getGrade());
        student1.study("Java OOP");

        Student student2 = new Student("李四", 19); // 使用重载的构造方法，分数默认为0
        System.out.println(student2.introduce());
        student2.setScore(92.0); // 通过setter修改分数
        System.out.println(student2.getName() + "的新分数: " + student2.getScore() + ", 等级: " + student2.getGrade());

        // 演示封装：尝试直接访问私有属性 (会导致编译错误)
        // student1.name = "赵六"; // 编译错误: name has private access in Student
        // student1.score = -10; // 编译错误
        // 必须通过getter/setter
        student1.setName(" 张三丰 "); // 测试setName的trim功能
        System.out.println("修改后学生1的名字: " + student1.getName());


        // --- 4. 继承演示 ---
        System.out.println("
--- 4. 继承 ---");
        // myDog 对象已经演示了继承 (Dog extends Animal)
        System.out.println(myDog.name + " 通过继承获得了 Animal 的 eat() 和 sleep() 方法。");
        myDog.eat();

        Cat myCat = new Cat("咪咪", "橘色");
        // myCat.displayInfo(); // Cat类没有displayInfo, 但可以自己添加或通过父类(如果父类有)
        System.out.println(myCat.name + "是一只" + myCat.furColor + "的猫。");
        myCat.eat();    // 来自父类 Animal
        myCat.sleep();  // 来自父类 Animal
        myCat.scratch(); // Cat特有的方法


        // --- 5. 多态演示 ---
        System.out.println("
--- 5. 多态 (方法覆盖) ---");
        // 父类引用指向子类对象
        Animal pet1 = new Dog("大黄", "拉布拉多", 4);
        Animal pet2 = new Cat("小花", "三花");
        Animal pet3 = new Animal("普通动物"); // 如果Animal不是抽象类

        System.out.print(pet1.name + " (Animal引用指向Dog对象) 发声: ");
        pet1.makeSound(); // 调用的是 Dog 类中覆盖的 makeSound()

        System.out.print(pet2.name + " (Animal引用指向Cat对象) 发声: ");
        pet2.makeSound(); // 调用的是 Cat 类中覆盖的 makeSound()

        pet3.makeSound(); // 调用 Animal 类自身的 makeSound()

        // 演示数组存储不同类型的动物对象 (利用多态)
        System.out.println("
多态在数组中的应用:");
        Animal[] zoo = new Animal[3];
        zoo[0] = new Dog("点点", "斑点狗", 2);
        zoo[1] = new Cat("雪球", "白色");
        zoo[2] = new Dog("将军", "德牧", 5); // 再来一只狗
        // zoo[2] = new Animal("未知生物"); // 如果Animal不是抽象类

        for (Animal animal : zoo) {
            System.out.print(animal.name + " 在动物园里 ");
            animal.makeSound(); // 动态绑定，调用各自对象的makeSound方法
            if (animal instanceof Dog) { // instanceof 判断对象真实类型
                Dog specificDog = (Dog) animal; // 向下转型 (Casting)
                specificDog.fetch("飞盘");
            } else if (animal instanceof Cat) {
                Cat specificCat = (Cat) animal;
                specificCat.scratch();
            }
        }


        // --- 6. 抽象类和抽象方法演示 ---
        System.out.println("
--- 6. 抽象类和抽象方法 ---");
        // AbstractAnimal abstractPet = new AbstractAnimal("幽灵"); // 编译错误：AbstractAnimal是抽象的，不能被实例化

        AbstractAnimal dogFromAbstract = new ConcreteDog("小黑", "田园犬");
        AbstractAnimal catFromAbstract = new ConcreteCat("灰灰", "灰色");

        System.out.print(dogFromAbstract.name + " (ConcreteDog对象) ");
        dogFromAbstract.makeSoundAbstractly(); // 调用ConcreteDog实现的抽象方法
        dogFromAbstract.eat(); // 调用AbstractAnimal中的具体方法

        System.out.print(catFromAbstract.name + " (ConcreteCat对象) ");
        catFromAbstract.makeSoundAbstractly(); // 调用ConcreteCat实现的抽象方法
        catFromAbstract.sleep();

        // 调用子类特有方法需要转型
        if (dogFromAbstract instanceof ConcreteDog) {
            ((ConcreteDog) dogFromAbstract).fetch();
        }


        System.out.println("
--- OOP概念演示结束 ---");
    }
}
