#!/usr/bin/python
# -*- coding: UTF-8 -*- 

print ("\nhello herman")

#使用中文需指定中文编码
print "你好,世界!\n"

#********************************************************************
#                       python变量
#   
#   python变量不需要声明，变量的赋值操作既是变量声明和定义的过程
#   python每个变量在内存中创建，都包括变量的标识，名称和数据这些信息
#   python每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建
#
#********************************************************************
icounter = 100   #赋值整形变量
fmiles = 1000.0  #浮点型
sname = "John"   #字符串
print icounter
print fmiles
print sname
print "\n"

#多个变量同时赋值则分配到相同的内存空间上
a = b = c = 2
a = 3
print id( a )
print id( b )
print id( c )
print "\n" 

#********************************************************************
#                   python的5个标准数据类型
#   
#   Numbers（数字）   :int , long , float , complex
#   String（字符串）
#   List（列表)       :是 Python 中使用最频繁的数据类型
#   Tuple（元组）     :元组用"()"标识,元素不能二次赋值，相当于只读列表
#   Dictionary（字典）:除列表以外python之中最灵活的内置数据结构类型
#                      列表是有序的对象结合，字典是无序的对象集合。
#********************************************************************

#字符操作:( + )连接字符  ( * )重复操作  [头下标 尾下标]截取字符
str = 'Hello World!'  
print str           # 输出完整字符串
print str[0]        # 输出字符串中的第一个字符
print str[2:5]      # 输出字符串中第三个至第五个之间的字符串
print str[2:]       # 输出从第三个字符开始的字符串
print str * 2       # 输出字符串两次
print str + "TEST"  # 输出连接的字符串''
print "\n" 

#打印列表,从左到右索引默认0开始的，从右到左索引默认-1开始，为空表示取到头或尾。
list = ['abcd', 786 , 2.23, 'john', 70.2 ]  
tinylist = [123, 'john'  ]  
print list            # 输出完整列表
print list[0]         # 输出列表的第一个元素
print list[1:3]       # 输出第二个至第三个的元素
print list[2:]        # 输出从第三个开始至列表末尾的所有元素
print tinylist * 2    # 输出列表两次
print list + tinylist # 打印组合的列表
print ' \n '

#元组的元素是不可变的,但可以进行连接组合.不能删除单个元素,但可以删除整个元组
#元组只有一个元素时,要添加逗号,下面示例说明
tuple = ('abcd', 786 , 2.23, 'john', 70.2 )  
tupl1 = ( 50, )
list  = ['abcd', 786 , 2.23, 'john', 70.2 ]  
#tuple[2] = 1000      # 元组中是非法应用
list[2]  = 1000      # 列表中是合法应用

#字典当中的元素是通过键来存取的，而不是通过偏移存取,字典用"{}"标识。
#字典由索引(key)和它对应的值value组成
dict = {}  
dict['one'] = "This is one"
dict[2] = "This is two"
tinydict = {'name':'john', 'code':6734, 'dept':'sales'  }  
print dict['one']       # 输出键为'one'的值
print dict[2]           # 输出键为 2 的值
print tinydict          # 输出完整的字典
print tinydict.keys()   # 输出所有键
print tinydict.values() # 输出所有值
print ' \n '

#字典的修改,删除
dict = {'Name':'Zara', 'Age':7, 'Class':'First'};
dict['Age'] = 8; # update existing entry
dict['School'] = "DPS School"; # Add new entry
print "dict['Age']: ", dict['Age'];
print "dict['School']  :", dict['School'];
del dict['Name']; # 删除键是'Name'的条目
dict.clear();     # 清空词典所有条目
del dict ;        # 删除词典
print ' \n '

#删除字典元素,不允许同一个建出现两个,如果出现,只记住后一个
#键必须不可变,所以可以 数字,字符充当,但不能用列表
dict = {'Name':'herman', 'Age':21, 'Name':'liuchangfa'}
print "dict['Name']:", dict[ 'Name' ]
dict = {('Name'):'herman', 'Age':21, 'Name':'liuchangfa'}  
print "dict['Name']:", dict['Name']    
#下面这行会出错,不能用可以改变的list作为键值
#dict = {['Name']:'herman', 'Age':21, 'Name':'liuchangfa'}

#******************************************************************** 
#                       python常用语句用法
#   这里只列出一些常用的自己不熟悉的语句用法
#******************************************************************** 

# for循环可以遍历任何序列的项目，如一个列表或者一个字符串
# First Example
for letter in 'Python':
    print 'Current Letter :', letter
# Second Example
fruits = [ 'banana', 'appale', 'mamgo' ]
for fruit in fruits:
    print 'Current fruit :',fruit
print "Good Bye"
print ' \n '

#for循环通过序列索引迭代
fruits = [  'banana', 'apple',  'mango'  ]  
for index in range(len(fruits)):
    print 'Current fruit :'  , fruits[index]  
print "Good bye!"
print ' \n '

#循环使用 else 语句
#for … else 表示这样的意思，for 中的语句和普通的没有区别，else中的语句会在循环
#正常执行完( 即 for 不是通过 break 跳出而中断的 )的情况下执行,while..else也一样
for num in range(10,20)  :  #to iterate between 10 to 20
    for i in range(2,num):  #to iterate on the factors of the number
        if num%i == 0:      #to determine the first factor
            j=num/i         #to calculate the second factor
            print '%d equals %d * %d' % (num,i,j)  
            break           #to move to the next number, the #first FOR
    else:                   # else part of the loop
        print num, 'is a prime number'
print ' \n '

#********************************************************************
#                       python处理时间
#
#********************************************************************

#获取当前时间
import time;
localtime = time.localtime(time.time())
print "Local current time :", localtime
print '\n'

#获取格式化的时间
import time;
localtime = time.asctime( time.localtime(time.time()) )
print "Local current time :", localtime
print '\n'

#获取某月日历
import calendar
cal = calendar.month(2008, 1)
print "Here is the calendar:"
print cal;
print '\n'

#********************************************************************
#                            python函数
#  1. 函数代码块以def关键词开头，后接函数标识符名称和圆括号()
#  2. 任何传入参数和自变量必须放在圆括号中间。圆括号之间可以用于定义参数
#  3. 函数的第一行语句可以选择性地使用文档字符串—用于存放函数说明
#  4. 函数内容以冒号起始，并且缩进
#  5. Return[expression]结束函数，选择性地返回一个值给调用方。
#     不带表达式的return相当于返回 None  
#********************************************************************
#####################################################################
#                               语法
def functionname( parameters ):
    "函数_文档字符串"
    function_suite
    return [expression]

####################################################################
#                           简单示例
#   可写函数说明
def changeme( mylist ):
    "修改传入的列表"
    mylist.append([1,2,3,4]);
    print "函数内取值: ", mylist
    return
             
#   调用changeme函数
mylist = [10,20,30];
changeme( mylist );
print "函数外取值: ", mylist
print '\n'

#################################################################### 
#                       四种正式参数类型
#
#1.必备参数须以正确的顺序传入函数。调用时的数量必须和声明时的一样
def printme( str ):
    "打印任何传入的字符串"
    print str;
    return;
#调用printme函数
printme('liuchangfa');

#2.命名参数,自动匹配参数,可以跳过不传的参数或者乱序传参
def printinfo( name, age ):
    "打印任何传入的字符串"
    print "Name: ", name;
    print "Age ", age;
    return;
#调用printinfo函数
printinfo( age=50, name="miki" );

#3.缺省参数的值如果没有传入，则被认为是默认值
def printinfo( name, age = 35 ):
    "打印任何传入的字符串"
    print "Name: ", name;
    print "Age ", age;
    return;         
#调用printinfo函数
printinfo( age=50, name="miki" );
printinfo( name="miki" );

#不定长参数,当使用时可能传入与当初声明时不同长度的参数
#示例
#   def functionname([formal_args,] *var_args_tuple ):
#       "函数_文档字符串"
#       function_suite
#       return [expression]

#加了星号（*）的变量名会存放所有未命名的变量参数
def printinfo( arg1, *vartuple ):
    "打印任何传入的参数"
    print "输出: "
    print arg1
    for var in vartuple:
        print var
    return;                      
# 调用printinfo 函数
printinfo( 10 );
printinfo( 70, 60, 50 );

###################################################################
#                           匿名函数
#python 使用 lambda 来创建匿名函数
#lambda的主体是一个表达式。仅仅能在lambda表达式中封装有限的逻辑进去
#lambda [arg1 [,arg2,.....argn]]:expression

# 可写函数说明
sum = lambda arg1, arg2: arg1 + arg2;
# 调用sum函数
print "相加后的值为 : ", sum( 10, 20 )
print "相加后的值为 : ", sum( 20, 20 )

###################################################################
#                       变量和局部变量
#定义在函数内部的变量拥有一个局部作用域，定义在函数外的拥有全局作用域

total = 0; # 这是一个全局变量
# 可写函数说明
def sum( arg1, arg2 ):
    #返回2个参数的和."
    total = arg1 + arg2; # total在这里是局部变量.
    print "函数内是局部变量 : ", total
    return total;
#调用sum函数
sum( 10, 20 );
print "函数外是全局变量 : ", total 
print '\n'

###################################################################
#                       python 文件IO
#               键盘输入输出,操作文件及目录等,
#
###################################################################

#读取键盘输入输出
#str = raw_input("Enter your input: ");
#print "Received input is : ", str

# 文件操作
# 打开或创建一个文件
fo = open("foo.txt", "wb+")
print "Name of the file: ", fo.name
print "Closed or not : ", fo.closed
print "Opening mode : ", fo.mode
print "Softspace flag : ", fo.softspace
fo.write( "Python is a great language.\nYeah its great!!\n");

# 打开一个文件
fo = open("foo.txt", "r+")
str = fo.read(10);
print "Read String is : ", str 
# 查找当前位置
position = fo.tell();
print "Current file position : ", position
# 把指针再次重新定位到文件开头
position = fo.seek(0, 0);
str = fo.read(10);
print "Again read String is : ", str
# 关闭打开的文件
fo.close()

#文件重命名和删除
import os
os.rename("foo.txt", "new.txt")
os.remove("new.txt")
print '\n'

###################################################################
#                       python 异常抛出
#   
###################################################################
try:
    fh = open("test/testfile", "w")
    fh.write("This is my test file for exception handling!!")
except IOError:
    print "Error: can\'t find file or read data"
else:
    print "Written content in the file successfully"
    fh.close()

