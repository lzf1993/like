package dynamic_8

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import method_6.Person

//#############   动态性   ####################
//动态性：类型是在运行时推断的，方法以及实参是在运行时候检查的。

def i = new File("");
//通过动态调用
i.getParent();



class P {
    def dream(){
    }
}

class M {
    def dream(){
    }
}

//编译的时候没有检查类型，运行的时候才检查. 所以传入任意类型都不会报错
void funcT(person){
    person.dream();
}

funcT(new P());
funcT(new M());
//也不会报错，只有运行的时候，才会报错，编译时没有检查
//funcT("");



//########################  为了更加严谨，增加检查，牺牲了动态性 ########################


// 关闭动态能力
@CompileStatic
class Lance {
   def dream(){
   }
}


//类型检查，会检查方法的调用，传入类型检查
@TypeChecked
void func(Lance person){
    person.dream();
}

//通过检查改对象有没有实现改方法。
void func2(person){
    //如果实现了这个方法就调用
    if(person.respondsTo('dream')){
        person.dream();
    }
}

//func(new Lance());
//func(new Person())
func("")

