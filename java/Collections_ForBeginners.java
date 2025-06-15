// java/Collections_ForBeginners.java

import java.util.ArrayList;
import java.util.Collections; // For utility methods like sort
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Java 集合框架入门 (Collections_ForBeginners)
 *
 * 集合框架 (Collections Framework) 是 Java 提供的一套用于存储和操作对象组的工具。
 * 它提供了一系列接口和类，帮助我们更高效地管理数据。
 *
 * 主要好处：
 * - 动态大小：集合的大小可以根据需要动态增长或缩小，不像数组那样固定。
 * - 提供了常用的数据结构：如列表、集、映射等。
 * - 提供了有用的算法：如排序、搜索等。
 * - 提高代码复用性和可维护性。
 *
 * 学习建议：
 * 1. 理解不同集合类型 (List, Set, Map) 的主要特点和适用场景。
 * 2. 掌握各种集合的常用操作方法。
 * 3. 注意泛型 `<>` 的使用，它能提供类型安全。
 */

public class Collections_ForBeginners {

    public static void main(String[] args) {
        System.out.println("--- Java 集合框架 (Collections Framework) 演示 ---
");

        // ===================================================================
        // 0. 泛型 (Generics) 简介
        // ===================================================================
        System.out.println("--- 0. 泛型 (Generics) ---");
        // 在集合中，我们经常看到 <String>, <Integer> 这样的写法，这叫做泛型。
        // 泛型允许我们在编译时指定集合中可以存储的数据类型，从而提供类型安全。
        // 如果不指定泛型，集合可以存储任何类型的对象，但在取出时需要强制类型转换，且容易出错。
        // List myList = new ArrayList(); // 不推荐，没有泛型
        // myList.add("Hello");
        // myList.add(123); // 可以添加不同类型
        // String text = (String) myList.get(1); // 运行时会抛出 ClassCastException
        System.out.println("泛型 `<Type>` 用于指定集合中元素的类型，如 `List<String>` 表示这是一个只能存字符串的列表。");
        System.out.println("这有助于在编译时发现类型错误，并避免运行时的类型转换错误。
");


        // ===================================================================
        // 1. List 接口 (列表)
        // ===================================================================
        System.out.println("--- 1. List 接口 ---");
        // List 是一个有序的集合（即元素有特定的顺序），并且允许存储重复的元素。
        // 常用的实现类有 ArrayList 和 LinkedList。

        // --- 1.1 ArrayList ---
        System.out.println("
~~~ 1.1 ArrayList (动态数组) ~~~");
        // ArrayList 底层是基于动态数组实现的。查询快 (基于索引)，增删相对慢 (可能需要移动元素)。

        // 创建一个存储字符串的 ArrayList
        List<String> fruitList = new ArrayList<>(); // 右边的 <> 可以省略类型 (类型推断, Java 7+)

        // a. 添加元素 (add)
        fruitList.add("苹果");
        fruitList.add("香蕉");
        fruitList.add("橙子");
        fruitList.add("苹果"); // List允许重复元素
        fruitList.add(2, "葡萄"); // 在指定索引位置添加元素
        System.out.println("水果列表 (初始化后): " + fruitList);

        // b. 获取元素 (get)
        String firstFruit = fruitList.get(0);
        System.out.println("第一个水果: " + firstFruit);
        System.out.println("第三个水果: " + fruitList.get(2));

        // c. 修改元素 (set)
        fruitList.set(1, "改良香蕉");
        System.out.println("修改香蕉后: " + fruitList);

        // d. 删除元素 (remove)
        fruitList.remove("橙子"); // 按内容删除 (第一个匹配的)
        System.out.println("删除橙子后: " + fruitList);
        fruitList.remove(2);    // 按索引删除 (删除“苹果”)
        System.out.println("删除索引2的元素后: " + fruitList);

        // e. 获取大小 (size)
        System.out.println("列表中的水果数量: " + fruitList.size());

        // f. 检查是否为空 (isEmpty)
        System.out.println("水果列表是否为空? " + fruitList.isEmpty());

        // g. 检查是否包含某个元素 (contains)
        System.out.println("列表中是否包含 '苹果'? " + fruitList.contains("苹果"));
        System.out.println("列表中是否包含 '芒果'? " + fruitList.contains("芒果"));

        // h. 遍历列表 (Iteration)
        System.out.println("遍历水果列表 (使用增强for循环):");
        for (String fruit : fruitList) {
            System.out.println("- " + fruit);
        }

        System.out.println("遍历水果列表 (使用迭代器 Iterator):");
        Iterator<String> iterator = fruitList.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("* " + fruit);
            // if (fruit.equals("苹果")) {
            //     iterator.remove(); // 使用迭代器删除元素是安全的
            // }
        }
        // System.out.println("使用迭代器删除苹果后: " + fruitList);


        // i. 清空列表 (clear)
        // fruitList.clear();
        // System.out.println("清空列表后: " + fruitList + ", 是否为空? " + fruitList.isEmpty());

        // j. 更多ArrayList示例：存储整数
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(5);
        numbers.add(15);
        System.out.println("数字列表: " + numbers);
        Collections.sort(numbers); // 使用 Collections 工具类排序
        System.out.println("排序后的数字列表: " + numbers);


        // --- 1.2 LinkedList ---
        System.out.println("
~~~ 1.2 LinkedList (链表) ~~~");
        // LinkedList 底层是基于双向链表实现的。增删快 (尤其是在列表头尾)，查询相对慢 (需要遍历)。
        // 它也实现了List接口，所以很多操作和ArrayList类似。
        // LinkedList 还实现了 Deque (双端队列) 接口，提供了在头尾操作的便捷方法。

        LinkedList<String> guestList = new LinkedList<>();
        guestList.add("张三");
        guestList.add("李四");
        System.out.println("嘉宾列表 (LinkedList): " + guestList);

        // LinkedList 特有的头尾操作
        guestList.addFirst("贵宾赵"); // 在头部添加
        guestList.addLast("嘉宾王");  // 在尾部添加
        System.out.println("添加头尾嘉宾后: " + guestList);

        String firstGuest = guestList.removeFirst(); // 移除并返回头部元素
        String lastGuest = guestList.removeLast();   // 移除并返回尾部元素
        System.out.println("移除的第一个嘉宾: " + firstGuest + ", 最后一个嘉宾: " + lastGuest);
        System.out.println("移除头尾后的嘉宾列表: " + guestList);

        System.out.println("LinkedList的get(0): " + guestList.get(0)); // 也有List的方法

        System.out.println("ArrayList vs LinkedList 简要对比:");
        System.out.println("- ArrayList: 查询快，增删（尤其是在中间）慢。占用连续内存。");
        System.out.println("- LinkedList: 查询慢，增删（尤其是在头尾）快。内存不一定连续。");
        System.out.println("选择哪个取决于具体的使用场景。大部分情况下，ArrayList的性能已经足够好。");


        // ===================================================================
        // 2. Map 接口 (映射)
        // ===================================================================
        System.out.println("
--- 2. Map 接口 ---");
        // Map 用于存储键值对 (key-value pairs)。
        // - 键 (Key) 是唯一的，不能重复。如果添加相同的键，新的值会覆盖旧的值。
        // - 值 (Value) 可以重复。
        // - 常用的实现类有 HashMap 和 TreeMap。

        // --- 2.1 HashMap ---
        System.out.println("
~~~ 2.1 HashMap (哈希表/散列表) ~~~");
        // HashMap 基于哈希表实现，提供快速的查找、添加和删除操作（平均时间复杂度O(1)）。
        // 键值对的存储顺序是不保证的，可能与插入顺序不同。

        // 创建一个存储 员工ID(Integer) 到 员工姓名(String) 的映射
        Map<Integer, String> employeeMap = new HashMap<>();

        // a. 添加键值对 (put)
        employeeMap.put(101, "爱丽丝");
        employeeMap.put(102, "鲍勃");
        employeeMap.put(103, "查理");
        employeeMap.put(104, "戴安娜");
        employeeMap.put(102, "鲍勃·史密斯"); // 键102已存在，值会被更新
        System.out.println("员工映射 (初始化后): " + employeeMap);

        // b. 获取值 (get)
        String name103 = employeeMap.get(103);
        System.out.println("ID为103的员工是: " + name103);
        System.out.println("ID为105的员工是: " + employeeMap.get(105)); // 如果键不存在，返回null

        // c. 根据键删除键值对 (remove)
        employeeMap.remove(104);
        System.out.println("删除ID为104的员工后: " + employeeMap);

        // d. 检查是否包含某个键 (containsKey)
        System.out.println("是否包含ID为101的员工? " + employeeMap.containsKey(101));
        System.out.println("是否包含ID为104的员工? " + employeeMap.containsKey(104));

        // e. 检查是否包含某个值 (containsValue)
        System.out.println("是否有员工叫 '爱丽丝'? " + employeeMap.containsValue("爱丽丝"));

        // f. 获取大小 (size) 和是否为空 (isEmpty)
        System.out.println("当前员工数量: " + employeeMap.size());
        System.out.println("员工映射是否为空? " + employeeMap.isEmpty());

        // g. 遍历 Map
        System.out.println("遍历员工映射:");
        // g.1 遍历键集合 (keySet)
        System.out.println("  通过 keySet 遍历:");
        for (Integer id : employeeMap.keySet()) {
            String name = employeeMap.get(id);
            System.out.println("    ID: " + id + ", 姓名: " + name);
        }

        // g.2 遍历值集合 (values) - 如果你只需要值
        System.out.println("  通过 values 遍历 (只获取姓名):");
        for (String name : employeeMap.values()) {
            System.out.println("    姓名: " + name);
        }

        // g.3 遍历键值对集合 (entrySet) - 最高效的遍历方式
        System.out.println("  通过 entrySet 遍历:");
        for (Map.Entry<Integer, String> entry : employeeMap.entrySet()) {
            System.out.println("    ID: " + entry.getKey() + ", 姓名: " + entry.getValue());
        }

        // h. 清空 (clear)
        // employeeMap.clear();
        // System.out.println("清空后的员工映射: " + employeeMap);


        // --- 2.2 TreeMap ---
        System.out.println("
~~~ 2.2 TreeMap (有序映射) ~~~");
        // TreeMap 基于红黑树实现，它会根据键的自然顺序或者构造时提供的比较器 (Comparator) 对键进行排序。
        // 增删查改的性能略低于HashMap (时间复杂度O(log n))，但提供了有序性。

        Map<String, Integer> wordCounts = new TreeMap<>(); // 键是String，会按字母顺序排序
        wordCounts.put("apple", 10);
        wordCounts.put("zebra", 5);
        wordCounts.put("banana", 12);
        wordCounts.put("orange", 8);

        System.out.println("单词计数 (TreeMap，按键排序):");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }


        // ===================================================================
        // 3. Set 接口 (集) - 简化版
        // ===================================================================
        System.out.println("
--- 3. Set 接口 ---");
        // Set 是一个不包含重复元素的集合。它不保证元素的顺序（特定实现如TreeSet除外）。
        // 主要用于存储唯一的元素，例如去重。

        // --- 3.1 HashSet ---
        System.out.println("
~~~ 3.1 HashSet (哈希集) ~~~");
        // HashSet 基于哈希表实现，提供快速的添加、删除和查找操作。不保证顺序。

        Set<String> uniqueNames = new HashSet<>();

        // a. 添加元素 (add) - 如果元素已存在，add方法返回false，不会重复添加
        System.out.println("添加 'Alice': " + uniqueNames.add("Alice"));
        System.out.println("添加 'Bob': " + uniqueNames.add("Bob"));
        System.out.println("再次添加 'Alice': " + uniqueNames.add("Alice")); // 不会成功，因为已存在
        uniqueNames.add("Charlie");
        System.out.println("唯一的名称集合 (HashSet): " + uniqueNames);

        // b. 检查是否包含元素 (contains)
        System.out.println("集合中是否包含 'Bob'? " + uniqueNames.contains("Bob"));
        System.out.println("集合中是否包含 'David'? " + uniqueNames.contains("David"));

        // c. 删除元素 (remove)
        uniqueNames.remove("Charlie");
        System.out.println("删除 'Charlie' 后: " + uniqueNames);

        // d. 获取大小 (size)
        System.out.println("集合中的唯一名称数量: " + uniqueNames.size());

        // e. 遍历 Set (顺序不保证)
        System.out.println("遍历唯一的名称集合 (顺序不保证):");
        for (String name : uniqueNames) {
            System.out.println("- " + name);
        }

        // 示例：使用Set给List去重
        List<Integer> numbersWithDuplicates = new ArrayList<>();
        numbersWithDuplicates.add(1);
        numbersWithDuplicates.add(2);
        numbersWithDuplicates.add(1);
        numbersWithDuplicates.add(3);
        numbersWithDuplicates.add(2);
        System.out.println("有重复的数字列表: " + numbersWithDuplicates);

        Set<Integer> uniqueNumbers = new HashSet<>(numbersWithDuplicates); // 直接用List构造HashSet
        System.out.println("去重后的唯一数字集合: " + uniqueNumbers);
        List<Integer> listWithoutDuplicates = new ArrayList<>(uniqueNumbers); // 转回List
        System.out.println("去重后转回的列表: " + listWithoutDuplicates);


        System.out.println("
--- 集合框架演示结束 ---");
        System.out.println("Java集合框架非常强大，还有更多高级特性和类（如Queue, Deque, LinkedHashSet, ConcurrentHashMap等）等待你去探索！");
    }
}
