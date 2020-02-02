groovy 元编程：

 方法拦截： 对一个方法进行拦截。

    定义一个类，会自动 实现 GroovyObject 接口

       public interface GroovyObject {
           Object invokeMethod(String name, Object args);
           Object getProperty(String propertyName);
           void setProperty(String propertyName, Object newValue);
           MetaClass getMetaClass();
           void setMetaClass(MetaClass metaClass);
       }

 1、拦截一个方法：

      //所有方法会被这儿拦截
      class Persons implements GroovyInterceptable{
          @Override
          Object invokeMethod(String name, Object args) {

          }
      }
