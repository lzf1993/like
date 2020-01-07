package loop_2

//第二部分： 循环


//1、循环

public void  test1(){

    //java循环
    println "java 循环:  ";
    for(int i= 0 ;i<10 ; i++){  //0 1 2 3 4 5 6 7 8 9
        print i+" ";
    }
    println "\n------------------ "



    //1、groovy提供的函数
    println "groovy IntRange循环:  ";
    for(j in new IntRange(true,0,9)){  //inclusive是否包含 to : 0,1,2,3,4,5,6,7,8,9
        print j+" "
    }
    println "\n------------------ "



    //2、groovy简写循环
    println "groovy in..循环:  ";
    for(a in 0..9){    //0 1 2 3 4 5 6 7 8 9
        print a+" "
    }
    def s = 0..9;
    println "\n"+s.class.getSimpleName()
    println "------------------ "



    //循环10次
    //times(Number self, @ClosureParams(value=SimpleType.class,options="int")  Closure closure)  -- 后面传入代码块
    //默认持有一个变量 it
    println "groovy times循环:  ";
    10.times {    //0 1 2 3 4 5 6 7 8 9
        print it+" ";
    }
    println " \n------------------ "


    //groovy递进
    println "groovy step递进:  ";
    10.step(25,2,{   //10 , 12  , 14 ... 24
        print it+" "
    })
    println "\n ---------------- "


    //groovy递减
    println "groovy step递减:  ";
    10.downto(4,{     //10 9 8 7 6 5 4
        print it+" "
    })
    println "\n ---------------- "



    //groovy递增
    println "groovy upto递增:  ";
    10.upto(19,{    //10 11 12 13 14 15 16 17 18 19
        print it+" "
    })
    println "\n ---------------- "

}





test1();