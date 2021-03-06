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

#### final关键字
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

### 字符串
Java字符串就是Unicode字符序列

### 格式化输出
#### 用于printf的转换符  

| 转换符 | 类型 | 举例 | 
| - | :-: | -: | 
| d | 十进制数| 159 | 
| x | 十六进制数 | 9f | 
| o | 八进制数 | 237 |
| f | 定点浮点数 | 15.9|
| e | 指数浮点数 | 1.59e+01|
| g | 通用浮点数 | - |
| a | 十六进制浮点数 | 0x1.fccdp3|
| s | 字符串 | Hello |
| c | 字符 | H |
| b | 布尔 | True |
| h | 散列码 | 42628b2 |
| % | 百分号 | % |
| n | 换行 | - |

另外还可以给出控制格式化输出的各种标志， 例如:  
  
```
System.out.println("%,.2f", 10000.0/3.0)
输出: 3,333.33
```

#### 用于printf的标志
| 转换符 | 类型 | 举例 | 
| - | :-: | -: | 
| + | 打印正数和负数符号 | +3333.33
| 空格 | 在整数之前添加空格 | | 333.33|
| 0 | 整数前面补0 | 003333.33 |
| ， | 添加分组分隔符 | 3,333.33 |


#### 日期和时间转换符    P59


