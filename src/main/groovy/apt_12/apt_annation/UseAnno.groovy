package apt_12.apt_annation;

class Cont {

    //@Check : 直接编译会报错，所以注释掉
    def fun() {
        Thread.sleep(2000)
//        //希望通过注解转换器将上面的实现改为下面的实现
//        def start = System.nanoTime()
//        Thread.sleep(2_000)
//        def use = System.nanoTime() - start
//        println use
    }

}


new Cont().fun();