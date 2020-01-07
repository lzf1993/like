package mop_9


//2、在单个对象上进行拦截: lance.metaClass.invokeMethod = {...}
class World {
    def dream(){
        println "lance is dream"
    }
}


//1、在单个对象上进行方法拦截  def lance = new Test2(); lance.metaclass 对象上进行处理
//2、在类上进行方法拦截 ：Test2.metaClass 就是在类上进行处理


//1、对象上进行方法拦截
def lance = new World()
lance.dream()

lance.metaClass.dream{
    println 'place dream'
}
lance.dream()


//覆盖 invokeMethod , 不会再调用真实的方法
lance.metaClass.invokeMethod={
    String name, Object arguments->
        System.out.println 'invoke'
        //查找方法，有就调用，使用 delegate
        def method = delegate.metaClass.getMetaMethod(name,arguments)
        if(method){
            method.invoke(delegate,arguments)
        }
}

lance.dream()


def lance1 = new World()
lance1.dream();

//实例,修改 plus 加方法
String.metaClass.plus = {
    CharSequence i->
        i
}

println("123"+"abc")

