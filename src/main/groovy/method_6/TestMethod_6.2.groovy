package method_6

import org.codehaus.groovy.runtime.metaclass.MissingMethodExceptionNoStack

class Rmb{

    def number;


    //+符合重载
    def plus(o){
        if(o instanceof  Number){
            new Rmb(number: number+o)
        }else if(o instanceof Rmb){
            new Rmb(number: number+o.number)
        }else{
            throw new MissingMethodExceptionNoStack("ddd")
        }
    }

    //switch(a){case(b): }
    def isCase(o){
         if(o instanceof  Rmb){
           o.number == 100;
        }else{
             false;
         }
    }


    @Override
    public String toString() {
        return "method_6.Rmb{" +
                "number=" + number +
                '}';
    }
}


def rmb = new Rmb(number: 100);
println rmb+1300;    //直接调用+报错
println rmb+rmb;     //直接调用+报错
//println rmb+" ";   //直接调用+报错

switch (rmb) {
    case new Rmb(number: 100):
        println "1"
        break;
    case 100:
        println "2"
        break
}



