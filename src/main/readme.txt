
1、定义一个类， 会实现 GroovyObject 接口
        class Person2 {
            def name;
            def dream(){
                println('i have a dream');
            }
        }
        public class Person2 implements GroovyObject {
            private Object name;
        }

2、不定义，会继承 Script -- 脚本

          package script_7.email
          mail{
              smtp{
                  auth = true;
                  host = 'smtp.163.com'
                  port = 25
              }
              env{
                  name = "lzf19930819@163.com"
                  pwd  = "liu19930819"
                  tolist = '1146700175@qq.com'
              }
          }

          public class email extends Script {
              public email() {
                  CallSite[] var1 = $getCallSiteArray();
              }
          }

3、定义一个闭包 ，会继承 Closure

           class TestClosure1{
               def static closure = {  //静态方法
                   println "this is " +this      //this  指的是 TestClosure2.class对象
                   println "owner is "+owner
                   println "delegate is "+delegate
               }
           }
