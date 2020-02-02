package mop_9


//改变方法第三种: methodMissing
class Man {

    //属性找不到
    def propertyMissing(String name) {
        return null
    }

    //属性找不到
    def propertyMissing(String name, def arg) {

    }

    //方法找不到
    def methodMissing(String name, def args) {
        println "methodMissing"
        return name
    }
}


def man = new Man()
//改方法不存在，会走到 methodMissing
println man.dream()