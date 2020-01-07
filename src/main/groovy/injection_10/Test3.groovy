package injection_10

// 3、使用mixin


//1、将get类混合进String类
class Get {
  def get(String url){
      url.toURL().text
  }
}

//将get类混合进入String类
String.mixin(Get)
"www.baidu.com/more".get("https://www.baidu.com/")


//2、将String类混合进入Get2类
@Mixin(String)
class Get2 {
    def get(String url){
        url.toURL().text
    }
}
new Get2().get("")
new Get2().substring("")


