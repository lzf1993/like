package mop_9

//1、在类上进行方法拦截: 实现该监听，进行拦截，所有调用，都会调到invokeMethod方法
class Persons implements GroovyInterceptable{
    static Persons instance;
    def init = false;
    def name

    def dream(){
      println 'i have a dream'
    }

    //单例类
    static Persons getInstance(){
        if (null == instance) {
            synchronized (Persons.class) {
                if (null == instance) {
                    instance = new Persons()
                }
            }
        }
        return instance
    }

    def init() {
        init = true
    }


    //该对象的，每一个方法执行，都会走到改方法 name:方法名  args:参数
    //metaClass.invokeMethod 直接调用，不会走到该方法
    @Override
    Object invokeMethod(String name, Object args) {
         //println "invoke method ${name}" 这个println方法是对象的
        if(name != 'init'){
            if(!init){
                System.out.println 'please invoke init first'
                return;
            }
        }
        System.out.println "invoke method ${name}"
        //判断是否实现了这个方法， 调用 respondsTo 方法去判断
        if(metaClass.invokeMethod(this,"respondsTo" ,name,args)){
            //如果方法存在，则分发到相应的方法
            metaClass.invokeMethod(this, name,args);
        }else{
            System.out.println 'missing method'
        }
    }
}


//1、普通调用方法
def p = new Persons(name:'liu')
p.dream()   //调用方法
p.invokeMethod('dream',null)

//2、原方法调用,先获取原方法，再进行调用
MetaMethod method = p.metaClass.getMetaMethod('dream',null)
method.invoke(p,null)

//3、通过类对象去调用某个方法
p.metaClass.invokeMethod(p,'dream',null)

//4、调用不存在的方法，也不会报错
p.dream1()   //调用不存在的方法，也会进入invokeMethod拦截方法



Persons.getInstance().init()
Persons.getInstance().dream()

