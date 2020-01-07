package method_6



//一、groovy 方法调用

class Person{
    def name  //定义属性
    def age

    //.name = "abc" 会默认调setName方法
    void setName(name){
        println "setName"
        this.name = name;
    }

    //.name 会默认调 getName方法
    def getName(){
        println "getName"
        return this.name;
    }

    void execute(x, y, z){
        println "$x  $y  $z"
    }
}

def person = new Person();


//1、直接在创建对象的时候赋值
def person2 = new Person(name:"hello", age:"world");

// x:1,y:2,z:3 当成一个map了
person2.execute(1,2, x:1,y:2,z:3) //int  int  map ， map会永远默认赋值给第一个参数


//2、默认调set，如果没有也会自动生成。
person.name = "jack";          //调用相应的get方法
person.setName("jack");        //会生成默认的set方法


//3、默认调用get方法
person.name
person."name";
def str = "name";   person."$str";
person["name"]


//4、如果不想调get或者set方法，想直接调属性
person.@name = "hello";  //直接对属性进行赋值
println person.@name;    //直接获取属性




//字符串处理
println " \n------------------------------ "

def str2 = "org.codehaus.groovy:groovy-all:2.3.11";
def s = str2.split(":");
def(group, name , version) = str2.split(":");

println s
println group
println name
println version



//第三部分

//声明一个借口
interface OnClickListener{
    void onclick();
}

//声明一个方法
void fun(OnClickListener listener){
    println listener.class  //class com.sun.proxy.$Proxy4
    listener.onclick()      //调用onclick方法时，通过代理的方式调用闭包的call方法
}

//将闭包赋值给 onClickListener
OnClickListener listener = {
    println "onclick"
}

//直接调闭包
fun listener




interface OnClickListener2{
    void before();

    void doing();

    void after();
}

//通过as ，表示声明的是一个 method_6.OnClickListener2，而不是一个闭包
OnClickListener2 listener2 = {
    println "onclick2"
} as OnClickListener2;

//执行闭包里面的方法
listener2.after()
listener2.before()
listener2.doing()














