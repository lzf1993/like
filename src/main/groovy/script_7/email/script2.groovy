package script_7.email


//#################  1、作用域

//本地作用域  -- 私有的
def name = "123"
//绑定作用域  -- 公有的
pwd = "456"

//调用绑定作用域
println binding.variables


//函数可以使用绑定作用域，不能使用本地作用域
void fun(){
//    println pwd    //可以正常使用
//    println name   //会报错
}
fun()


//#################  2、调用其他脚本  #################

//方式1、使用系统api
//声明变量
args = []
args[0] = 'email.groovy'

//执行脚本
evaluate(new File('script.groovy'))


//方式2、参考系统api，自己处理
class Test2 {
    static void main(args){
        println '111111'
        def binding = new Binding()
        binding.setVariable("args",['src/main/groovy/script_7/email/email.groovy'])
        GroovyShell shell = new GroovyShell(getClass().getClassLoader(),binding)
        shell.evaluate(new File('src/main/groovy/script_7/email/script.groovy'))
    }
}



//456
//Caught: groovy.lang.MissingPropertyException: No such property: name for class: script2
//groovy.lang.MissingPropertyException: No such property: name for class: script2
//at script2.fun(script2.groovy:13)
//at script2.run(script2.groovy:16)
//Process finished with exit code 1






