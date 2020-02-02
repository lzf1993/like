package json_xml_swing_build_13.builde

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import groovy.json.JsonSlurper


//============================1、json序列化：将其他类型转换成json ==========================
println("\n json序列化: ")


//1、通过三个 ""构建一个json
println("\n=============== 生成json, 包一层结构 ================")
def str = """{"name":"lzf","wife":"wyy"}"""
println(str)



//2、通过 JsonBuilder 生成json, 包一层结构
println("\n=============== 生成json, 包一层结构 ================")
def builder1 = new JsonBuilder()
//方法不存在，但是可以调用，类似于元编程，方法注入： methodMissing();
//{"json":{"first":"lzf","last":"wyy"}}
builder1.json{
    first 'lzf'
    last 'wyy'
}
println builder1


//3、通过 JsonBuilder 生成json
println("\n=============== 生成json ================")
def builder2 = new JsonBuilder()
//{"first":"lzf","last":"wyy"}
builder2{
    first 'lzf'
    last 'wyy'
}
println builder2


//4、将一个object转成一个json
println("\n=============== 将一个object转成一个json ================")
class Person{
    def first;
    def last;
}
def p = new Person(first:'lzf',last:'wyy');
def b = new JsonBuilder(p)
println(b.toString())
//使用工具类转成json
println JsonOutput.toJson(p)
//使用工具类格式化输出json
println JsonOutput.prettyPrint(JsonOutput.toJson(p))



//============================2、json反序列化：将json转换成相应类型 ==========================


//5、json反序列化
println("\n json反序列化: ")
println("\n=============== 将json转换成object ================")
def slurper = new JsonSlurper()
//转换上面字符串
def st = slurper.parseText(str)
def object = slurper.parseText(JsonOutput.toJson(p))
//直接转成成一个相应的对象
Person person = slurper.parseText(JsonOutput.toJson(p))
println st
println object
//获取相应的属性
println person.last
println person.first




