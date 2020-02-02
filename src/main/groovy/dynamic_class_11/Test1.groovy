package dynamic_class_11

//############## 动态类 ##############

//1、运行时，自定义生成一个类， 使用 Expando

// 键值对 ==> 属性名：属性值  方法名：方法值
def expando = new Expando(name:'hello', fun1:{
   println "fun1"
})
expando.height = 100   //定义height属性
expando.fun2 = {       //定义fun2方法
    println "fun2"
}

//调用动态类创建的属性、方法
println expando.name
println expando.height
expando.fun1()
expando.fun2()