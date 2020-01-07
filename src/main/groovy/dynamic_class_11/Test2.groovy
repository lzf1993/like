package dynamic_class_11

// 如果实现     GroovyInterceptable 接口，调用所有方法都会回调 invokeMethod 方法
// 如果没有实现 GroovyInterceptable  接口，如果调用一个不存在的方法，则会回调到 methodMissing

//2、方法合成
class Person {
    //方法找不到
    def methodMissing(String name, def args) {
        println "methodMissing"
        //判断是"play"开头，则注入该方法，并且调用
        if(name.startsWith('play')){
            Person p = this
            //注入改方法
            p.metaClass."$name" = {
                println "invoke $name"
            }
            "$name"(args)
        }
        return name
    }
}


def p = new Person()
p.playBack();  //第一次调，不存在会注入
p.playBack();  //后续调用，存在，则直接调起
p.playBack();