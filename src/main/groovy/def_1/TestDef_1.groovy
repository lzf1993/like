package def_1

// 第一部分 ： 基本数据类型，已经定义



class TestDef_1 {

    //1、类型定义
    static void test(){

        //定义类型
        def k = 'a';           //string 类型
        def i = "groovy";      //string 类型
        def h = 10;            //int 类型
        def b = [10,1,1,1,1]   //数组类型

        //转义类型
        def j = "b" as char;  //转义类型为char类型
        j = "good";

        //拼接
        String.format("hello %s world",j);
        def s = "groovy is ${k}"       //双引号，则对$进行转义，单引号则不进行转义。
        def a = "groovy ${->j} work"   //相当于一个闭包 -- 就是一个可执行的代码块。

        //输出
        println h;
        println b;
        println k;
        println i;
        println s;
        println a;
        println j.class.getSimpleName();   //直接获取class
        println "groovy"
        println "-----------------------------\n";
    }



   //2、函数没有返回值，默认返回最后一行值
    static String test1(){
        "groovy"
    }


    //2、多行字符串
    static void test2(){
        def j = "java \n groovy"  //使用
        def i = """<head> "
                  " 标题 " 
                "</head>" 
                "<body>" 
                   " this is body..." 
                "</body>"""
        println i;
        println j;
        println "-----------------------------\n";
    }


    //3、执行命令
    static void test3(){
        def i = 9;
        if(i.asBoolean()){  //是int类型，则判断int值是否为0
            print "is true";
        }
        def str = "cmd /c groovy -v";
        println str.execute().getText()  //execute(final String self)      ||   getText(Process self)

         //字符串提供的方法
        println "groovy".indexOf(0);
        println "groovy" - "vy";
        println "groovy" + "123";
        println 11 * 22;
        println "-----------------------------\n";
    }



    static void main(String[] args){
        test();
        println test1();
        test2()
        test3();
    }


}
