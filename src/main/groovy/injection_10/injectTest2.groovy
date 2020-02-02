package injection_10

/* 使用 metaclass 实现方法注入
         "<<"  注入新方法  ，
         "="   注入或者拦截方法，
   推荐使用 = , 因为使用 "<<" 时，如果该方法已经存在，则会报错
*/

//1、往类上注入普通新方法： 任何对象都可以调用这个方法
println '===================== 类<<注入 =================='
String.metaClass.get << {
   println delegate.toString().toURL().text
}
//调用普通方法
"https://www.baidu.com".get()



//2、往具体的对象上注入方法
println '===================== 对象<<注入 =================='
def str = "https://www.baidu.com"
str.metaClass.get = {
    println delegate.toString().toURL().text
}
//调用方法
str.get()
"https://www.baidu.com".get()   //也能调用，因为默认和 str是同一个地址， new出来的就不行 def str1 = new String("https://www.baidu.com/")  str1.get();



//3、类上注入静态方法,都可以直接调用静态方法
println '===================== 类注入静态方法 =================='
String.metaClass.'static'.prinlnClass = {
    println delegate
}
//调用方法
"111".prinlnClass()



//4、构造函数,拦截, 方法名必须是 constructor
println '===================== 构造函数拦截 =================='
String.metaClass.constructor = {
    Calendar calendar ->
        new String(calendar.getTime().toString())
}
println new String(Calendar.instance);



//5、拦截类普通方法
println '\n===================== 拦截普通方法 =================='
String.metaClass.endsWith={
    String s->
    println "test "
}
//调用
"dd".endsWith("s")



//6、统一注入多个方法
println '\n===================== 注入多个方法 =================='
String.metaClass{
    //注入方法
    get1 = {
        delegate.toString().toURL().text
    }
    //注入静态 printlnClass 方法
    'static' {
        printlnClass = {
            println "============="
            println delegate
        }
    }
    //拦截构造方法
    constructor = {
        Calendar calendar ->
            new String(calendar.getTime().toString())
    }
}
//打印类型 ： groovy.lang.ExpandoMetaClass@437da279[class java.lang.String]
//说明，给 metaClass 注入方法后，使用 的metaClass是 ExpandoMetaClass， 关系相当于继承
println String.metaClass



//7、直接使用 ExpandoMetaClass 注入方法
println '\n===================== 使用ExpandoMetaClass注入 =================='
def emc = new ExpandoMetaClass(String)
emc.get3 = {  //注入get方法
    delegate.toString().toURL().text
}
emc.initialize() //初始化
String.metaClass = emc //进行替换
println "https://www.baidu.com/".get3() //调用
String.metaClass = null  //置为空
//println "https://www.baidu.com/".get3()  //在调用会报错



//8、要注意的问题： 对象的work方法，run方法都是通过动态调用结点方式执行，但是work方法内部调用的run方法不是的
 //是直接调用的run方法， 所以后面改变了run方法，只是改变了动态调用结点。
println '===================== run =================='
class Test33 {
    public void work(){
        run()
    }
    public void run(){
        System.out.println("run")
    }
}

Test33.metaClass.run = {
    println "groovy run"
}
new Test33().work()