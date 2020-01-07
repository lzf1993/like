package dynamic_class_11

//方法委托

class Work1{
    def execute1(){
        println "execute1"
    }
}


class Work2{
    def execute2(){
        println "execute2"
    }
}

//1、通过简单方式进行委托
class WorkManger{
    Work1 work1 = new Work1();
    Work2 work2 = new Work2();

    //可以通过 methodMissing 实现 直接 wm.execute1()
    def methodMissing(String name, def args) {
        WorkManger wm = this
        if(work1.respondsTo(name,args)){
            //注入改方法
            wm.metaClass."$name" = {
                work1.invokeMethod(name,it)
            }
            //调用一次注入的方法
            "$name"(args)
        }else if(work2.respondsTo(name,args)){
            //注入改方法
            wm.metaClass."$name" = {
                work2.invokeMethod(name,it)
            }
            //调用一次注入的方法
            "$name"(args)
        }
    }

}

def wm = new WorkManger()
wm.work1.execute1()
wm.execute1()
wm.execute2()
println "-------------------------"


//2、自己封装实现委托
class WorkManger1{
    {
        delegate(Work1,Work2);
    }

    def delegate(Class... classes){
        //返回对象数组
        def objects = classes.collect{
            it.newInstance()
        }
        WorkManger1 vm = this
        //拦截 methodMissing 方法
        vm.metaClass.methodMissing = {
            String name, def args ->
                //查找调用方法的实现对象
                def object = objects.find {
                    it.respondsTo(name,args)
                }
                //如果查找到
                if(object){
                    //注入方法
                    vm.metaClass."$name" = {
                        object.invokeMethod(name,it)
                    }
                    //调用注入的方法
                    invokeMethod(name,args)
                }
        }
    }
}

def wm1 = new WorkManger1()
wm1.execute1()
wm1.execute2()
println "-------------------------"


//3、通过注解
class WorkManger2{
    @Delegate Work1 work11 = new Work1()
}
new WorkManger2().execute1()












