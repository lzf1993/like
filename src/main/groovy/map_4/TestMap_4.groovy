package map_4



void test(){
    //定义一个map
    def map = ['B':'Baidu','A':"Alibaba",'T':'Tencent'];
    println map.getClass().simpleName;

    //1、跟进键取值的三种方式。
    println "根据键获取值："
    println "A: "+map.A
    println "B: "+map.'B'
    println "T: "+map['T']
    println " \n------------------- "


    //2、变量map
    println "遍历map集合："
    map.each {
        key,value ->
            println "${key} = ${value}";
    }
    println " \n------------------- "


    //3、调用find方法
    println "find方法查找元素："
    println map.find {
        key,value ->
            value=~"a";
    }
    println " \n------------------- "


    //4、操作符重载
    println "map集合重载+："
    println map + ["a":"aac"];    //增加元素
    println " \n------------------- "

    println map*.getKey();
    map << ["a":"aac"];
    println "添加后元素："+map;

}


test();