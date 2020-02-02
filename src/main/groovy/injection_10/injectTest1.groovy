package injection_10

//拦截方法、注入方法、合成方法   -- 都是操作一个类的方法


//运行时元编程： 1、category 分类注入 跟继承类似
//            2、使用metaclass(方法进行拦截)
//            3、使用mixin
//编译时元编程： apt 注解处理功能 ButterKnife


//注入方法、拦截方法

//################ 1、category 分类， 使用use 对性能有影响 ################

//1、普通方式进行扩展分类
class Test2 {
    //扩展方法，给String扩展一个 get方法
    static def get(String self){
        self.toURL().text
    }
}

//使用分类
use(Test2){
    //调用字符串的get方法
    println "https://www.baidu.com/".get()
}



//2、使用注解方式进行分类, 在类上声明
@Category(String)  //表示一个String类型的分类
class StringUtils{
    //注入一个get方法
    def get1(){
       toString()
    }
}

//使用分类 ，两个分类都注入了get方法，则调用的是最后的那个 Test2注入的get方法
//先从 Test2中找get方法， 如果没有则从StringUtils中找，如果还没有则从String中找
use(StringUtils, Test2){
    println  "http://yapi.afpai.com/".get1()
}

