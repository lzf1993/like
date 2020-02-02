package mop_9


//2、在单个对象上进行拦截: lance.metaClass.invokeMethod = {...}
class World {
    def dream(){
        println "normal : lance is dream"
    }
}


//1、在对象 上拦截单个方法  lance.metaClass.dream{ println 'place dream'  }
//2、在对象上，通过metaClass 拦截所有方法： lance.metaClass.invokeMethod={ String name, Object arguments-> }



println '===================== normal =================='
def lance = new World()
lance.dream()


//（1）、改变单个对象的方法
println '===================== dream place =================='
lance.metaClass.dream{
    println 'place： is my dream'
}
lance.dream()


//（2）、覆盖 invokeMethod，拦截所有方法 , 不会再调用真实的方法， 跟实现 invokeMethod 表现类似，也只是对当前对象管用
println '===================== invokeMethod place =================='
lance.metaClass.invokeMethod={
    String name, Object arguments->
        System.out.println 'start invoke'
        //查找方法，有就调用，使用 delegate
        def method = delegate.metaClass.getMetaMethod(name,arguments)
        if(method){
            method.invoke(delegate,arguments)
        }
}
lance.dream()


//上面拦截后，对新的对象不起作用
println '===================== normal =================='
def lance1 = new World()
lance1.dream();



//实例,修改 plus 加方法
String.metaClass.plus = {
    CharSequence i->
        i
}

println("123"+"abc")

