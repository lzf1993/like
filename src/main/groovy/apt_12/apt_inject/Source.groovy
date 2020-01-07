package apt_12.apt_inject

//编译的时候，拦截方法，或者注入方法
class Contents {

   def soutMsg() {
       println("msg")
   }
}

new Contents().soutMsg()