package script_7.script

class TestFile_7 {

    //groovyc -d classes TestFile_7.groovy              -- 编译 TestFile_7.class文件
    //jar -cf file.jar -C classes . -C manifest/ .      -- 打包 成 file.jar 文件
    // org.codehaus.groovy.runtime.ExtensionModule
    //如何扩充一个实例方法和静态方法，在打包的时候
    //声明的时候，都是static方法
    static File start(File self , Closure closure){
        closure();   //调用闭包
        return self  //返回自己
    }
}
