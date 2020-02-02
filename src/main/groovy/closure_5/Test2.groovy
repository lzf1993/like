package closure_5

//声明闭包
def TestClosure1 = {
    println "this is " +this            //closure_5.Test2对象
    println "owner is "+owner           //closure_5.Test2对象
    println "delegate is "+delegate     //closure_5.Test2对象
}
//通过.访问属性，实际调用的是get方法
println TestClosure1.maximumNumberOfParameters  //需要的参数个数
println TestClosure1.parameterTypes   //参数类型
//调用闭包
println " \n-------- TestClosure1 --------------  "
TestClosure1()




class TestClosure2{
    def static closure = {  //静态方法 , 所以是类对象。
        println "this is " +this              //this  指的是 TestClosure2.class对象
        println "owner is "+owner             //this  指的是 TestClosure2.class对象
        println "delegate is "+delegate       //this  指的是 TestClosure2.class对象
    }
}


class TestClosure3{
    def closure = {  //普通方法
        println "this is " +this     //this 指的是 TestClosure3 对象
        println "owner is "+owner
        println "delegate is "+delegate
    }
}


class TestClosure4{
    def closure1 = {  //普通方法
        def closure2 = {
            println "this is " + this           //this 指的是 TestClosure4 对象
            println "owner is " + owner         //closure1 对象
            println "delegate is " + delegate   //closure1 对象
        }
        closure2();
    }
}

println " \n-------- TestClosure2 --------------  "
TestClosure2.closure();

println " \n--------- TestClosure3 -------------  "
new TestClosure3().closure();

println " \n--------- TestClosure4 ------------ "
new TestClosure4().closure1();


// 总结 ： this：指的是定义它的时候，所在类的this -- 对象，或者类对象
//        owner: 定义它的时候，所在类的对象
//        delegate:默认是owner, 但是是可以修改的
//        具体差别见 TestClosure4

//例如：一个闭包想调用一个类中的某个函数，则是无法直接调用的，只能通过设置代理方式进行调用
class TestFunc{
    def func(){
        println "func1";
    }
}

def func1(){
    println "func2";
}

def TestClosure5 = {
    func();  //直接是无法调用的
}

TestClosure5.delegate = new TestFunc()  //通过设置代理的方式，可以调用,但是当有两个一样的方法时，会优先选择当前的方法。
//closure.resolveStrategy = Closure.OWNER_FIRST;  //使用默认，是使用owner  -- 输出 ：func2
TestClosure5.resolveStrategy = Closure.DELEGATE_FIRST  //代理优先，使用delegate -- 输出：fun1
//closure.resolveStrategy = Closure.TO_SELF       //在闭包里面找，会报错，闭包里面没有。
println " \n--------- TestClosure5 ------------ "
TestClosure5()

