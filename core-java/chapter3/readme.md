Java的数据主要分为**引用类型**和**基本类型(primitive type)**

####基本数据类型
**整型**  
1. int, 占4字节
2. short， 占2字节
3. long, 占8字节
4. byte 1字节  

备注:长整型数值有一个后缀L或l. 十六进制数值有一个前缀0x或0X. 8进制有一个前缀0, 例如010对应8进制8。 加上前缀0b或0B就可写二进制数, 例如0b1001就是9.  
**浮点数**
1. float, 占4字节, 有效位数为6-7位
2. double, 占8字节， 有效位数为15位    

备注: 浮点数采用二进制表示， 计算的时候会损失精度  
**char类型, 占2个字节  **
  
备注: char类型是一个采用UTF-16编码表示Unicode码点的代码单元
**boolean类型**

####final关键字
关键字final表示这个变量只能被赋值一次


#### 数值之间的转换
```
int n = 12345678;
float f = n;
当上面数值进行二元操作时(例如n+f, n是整数, f是浮点数), 先要将两个操作数转换成同一类型， 然后在进行计算
```
- 如果两个操作数有一个是double类型， 另外一个操作数就会转换成double类型
- 否则,如果其中一个操作数是float类型， 另外一个操作数转换为float类型
- 否则, 如果其中一个数long类型， 另外一个转换成long类型
- 否则, 两个操作数都转换为int类型

###字符串
Java字符串就是Unicode字符序列

###格式化输出
####用于printf的转换符  

| Name | Academy | score | 
| - | :-: | -: | 
| Harry Potter | Gryffindor| 90 | 
| Hermione Granger | Gryffindor | 100 | 
| Draco Malfoy | Slytherin | 90 |
