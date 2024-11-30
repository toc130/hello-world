#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# 1. 基本数据类型和变量
print("\n1. 基本数据类型和变量示例：")
# 整数
age = 25
# 浮点数
height = 1.75
# 字符串
name = "张三"
# 布尔值
is_student = True
# 列表
hobbies = ["读书", "游泳", "编程"]
# 字典
person = {"name": "张三", "age": 25, "city": "北京"}
# 元组（不可变列表）
coordinates = (12.5, 42.3)

print(f"姓名：{name}, 年龄：{age}, 身高：{height}米")
print(f"是否是学生：{is_student}")
print(f"爱好：{hobbies}")
print(f"个人信息：{person}")
print(f"坐标：{coordinates}")

# 2. 条件控制
print("\n2. 条件控制示例：")
score = 85

if score >= 90:
    grade = "优秀"
elif score >= 80:
    grade = "良好"
elif score >= 60:
    grade = "及格"
else:
    grade = "不及格"

print(f"分数：{score}, 等级：{grade}")

# 3. 循环
print("\n3. 循环示例：")
# for循环
print("for循环遍历列表：")
for hobby in hobbies:
    print(hobby)

# while循环
print("\nwhile循环计数：")
count = 0
while count < 3:
    print(f"计数：{count}")
    count += 1

# 4. 函数定义和使用
print("\n4. 函数示例：")
def calculate_bmi(weight, height):
    """计算BMI指数"""
    bmi = weight / (height ** 2)
    return round(bmi, 2)

def get_bmi_status(bmi):
    """根据BMI判断身体状况"""
    if bmi < 18.5:
        return "偏瘦"
    elif bmi < 24:
        return "正常"
    elif bmi < 28:
        return "偏胖"
    else:
        return "肥胖"

weight = 70
bmi = calculate_bmi(weight, height)
status = get_bmi_status(bmi)
print(f"身高：{height}米，体重：{weight}公斤")
print(f"BMI：{bmi}，身体状况：{status}")

# 5. 类和对象
print("\n5. 类和对象示例：")
class Student:
    def __init__(self, name, age, score):
        self.name = name
        self.age = age
        self.score = score
    
    def introduce(self):
        return f"我叫{self.name}，今年{self.age}岁，考试分数是{self.score}分"
    
    def study(self, subject):
        return f"{self.name}正在学习{subject}"

# 创建学生对象
student = Student("李四", 18, 95)
print(student.introduce())
print(student.study("Python编程"))

print("Hello, World!")
