# Java的GBK编码项目统一转换成utf-8格式

- 在Explore中进入cmd，使用如下命令运行jar包

```bash
java -jar GBK2UTF8.jar
```
- 运行界面
![运行过程演示](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228134639.png)
1. 输入源路径
2. 输出目录

---
- 构建
> 使用common io批量将java编码从GBK转UTF-8

- pom.xml
```xml
<dependencies>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.8.0</version>
    </dependency>
</dependencies>
```

- GBK2UTF8.java
```java
public class GBK2UTF8 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入源路径：");
        //GBK编码格式源码路径
        String srcDirPath = scanner.nextLine();
        System.out.println("请输入存储目录：");
        //转为UTF-8编码格式源码路径
        String utf8DirPath = scanner.nextLine();
        //String srcDirPath = "C:\\Users\\Keen\\Desktop\\filter-listener-day01-all";
        //
        //String utf8DirPath = "D:\\UTF8\\src";

        //获取所有文件      extensions:new String[]{"java"} //仅获取java文件
        Collection<File> javaGbkFileCol =  FileUtils.listFiles(new File(srcDirPath), null, true);

        for (File javaGbkFile : javaGbkFileCol) {
            //UTF8格式文件路径
            String utf8FilePath = utf8DirPath+javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
            System.out.println(utf8FilePath);
            //使用GBK读取数据，然后用UTF-8写入数据
            FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
        }
    }
}

```
- 打包
  打包方式参照：
  [IDEA+Maven打jar包](https://blog.csdn.net/branwel/article/details/79918018)

- IDEA自带的打包工具

    打无依赖的jar包

1. 如果有以下文件夹则，删除工程目录中的META-INF文件

![image-20201228144632551](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228144632.png)

2. 选择工程上方【File】菜单选择【Project Structure】

![image-20201228144716821](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228144716.png)

3. 点击【+】 选择【JAR】，【From modules with dependencies】点击【ok】

![image-20201228144836898](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228144836.png)

4. 点击【1】选择你要打包的Module，点击【2】选择你的主类（也就是你的程序的入口）然后点击【ok】

![image-20201228145104449](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228145104.png)

5. 【1】是要打的jar包的名字，【2】是jar包的输出路径，【3】是程序所需要的jar依赖。因为这里是打没有依赖的jar包，所以将【3】里面不需要的jar包删掉就行。最后点击【ok】，将生成一个META-INF文件夹，里面为一些配置属性。

![image-20201228145722466](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228145722.png)

6. 构建jar包，构建后在输出目录下jar包已经打好了，打好的jar包在out目录下。（如果此前已经有jar包，先clean，再build）

![image-20201228145906830](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228145906.png)![image-20201228145933015](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228145933.png)

![image-20201228150315028](https://cdn.jsdelivr.net/gh/lizhangjie316/img/2020/20201228150315.png)



<font color='red'>PS:  提示错误   .jar中没有主清单属性</font>

- 解决方式

```bash
在jar包的形式下，以压缩文件方式打开，找到MANIFEST.MF，增加  Main-Class: com.lzj.GBK2UTF8  不同的启动类对应修改主类即可。
然后再保存。
```



# 参考内容

- [使用common io批量将java编码从GBK转UTF-8](https://www.oschina.net/code/snippet_97118_11332)

- [idea的打包方式汇总](https://blog.csdn.net/branwel/article/details/79918018)
