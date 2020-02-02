package mop_9


//方法的实现根据后端下发 : 运行时元编程

class Man1{
    def dream(){
       println 'i have a dream'
    }
}

def man = new Man1();
man.dream()

def scanner = new Scanner(System.in)
Thread.start {
    while (true) {
        def msg = scanner.nextLine()
        if (msg == 'exit') {
            break
        }
        //有这个方法，则直接执行
        if (man.respondsTo(msg)) {
            man."$msg"()
        } else { //没有这个方法，则， 方法：方法实现
            def (name, body) = msg.split(':')
            //拦截一次后，后面所有的都会执行这个拦截方法
            man.metaClass."$name" = {
                evaluate(body)  //执行脚本
            }
        }
    }
}
