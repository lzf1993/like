package closure_5


//闭包的定义及其使用
void test(){

    //1、定义闭包，默认一个参数， it
    def closure990 = {
        println it
    }
    println closure990.getClass().simpleName;

    //闭包调用
    closure990.call()   //调用闭包
    closure990()        //直接调用闭包

    //闭包默认能接受一个参数
    closure990("hello world");


    //2、定义一个闭包，不能接收参数
    def closure2 = {
        ->
        println "groovy"
    }
    //调用闭包
    closure2();


    //3、定义一个闭包，接收多个参数
    def closure3 = {
        i,j,m ->
        println "groovy "+ i+" "+ j;
    }
    //调用闭包
    closure3("hello","world","java");


    //4、定一个闭包，给参数设置默认值
    def closure4 = {
        i =" groovy ",j ->
            println  i+" "+ j;
    }
    //调用闭包
    closure4("world");                //传一个值
    closure4("hello", "world");     //也能传两个值


    //5、柯里化闭包，绑定参数，只能给剩余参数传值。
    def closure5 = {
        i ,j , m->
            println  "i: "+i+" j: "+ j+" m: "+m;
    }
    //柯里化闭包： 给第三个参数传值，传的参数是: hello world
    def cols= closure5.ncurry(2,"hello world")
    //新的闭包，只能给i和j传值
    cols.call("i ","j");
}


test();




//定义一个函数，传入一个闭包
void fun(closure){
    closure();  //调闭包的方法，这个()表示调了 call()方法，则也说明传入的闭包要有call方法。
}

interface Action{
    void call();
}


//调用改函数，最终也调了call方法的
fun(new Action() {
    @Override
    void call() {
       println "call";
    }
})

class Action1{
    def call(){
       println "call2"
    }
}

//实例化一个对象，后面的()，代表调用了里面的call方法
//new Action1()();

