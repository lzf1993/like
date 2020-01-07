package apt_12.apt_inject

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * 拦截类
 */
@GroovyASTTransformation
class InjectAST implements ASTTransformation{
/**
 * 通过拦截原始类的方法，拦截或者注入新的实现
 * @param astNodes
 * @param sourceUnit
 */
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        //sourceUnit.AST.classes 拿到所有的类，
        //然后找 名字 为 'apt_12.apt_inject.Contents' 的类 ，
        //然后再拿到所有的方法，然后再 ‘sountMsg’ 的方法
        //dsl 配合 groovyConsole 使用
        sourceUnit.AST.classes.find{
            //注意这个名字，要是全包名
            it.name == 'apt_12.apt_inject.Contents'
        }?.methods?.find({
            it.name == 'soutMsg'
        })?.with {
            //代码根结点
            BlockStatement block = code;
            //代码结点，有几行代码，则list就有几个元素
            List<Statement> list =  block.statements


            //###################### 添加我们自己的实现 ######################

            //方式1、buildFromSpec : 把原来的实现清除，添加新的实现 -- 方法拦截
            def methods1 = new AstBuilder().buildFromSpec {
                expression {
                    methodCall {
                        variable("this")
                        constant("println")
                        //将打印内容 msg 改为 replace
                        argumentList {
                            constant("replace")
                        }
                    }
                }
            }
            //block.statements.clear()
            //block.statements.addAll(methods1);


            //方式2、buildFromSpec ： 把原来的实现清除，添加新的实现 -- 方法拦截
            //methods2 是一个 BlockStatement对象数组
            def methods2 = new AstBuilder().buildFromString("""println '123321' """)
            //block.statements.clear()
            //block.statements.addAll(methods2[0].statements);


            //方式3、buildFromCode： 把原来的实现清除，添加新的实现 -- 方法拦截
            //methods3 是一个 BlockStatement对象数组
            def methods3 = new AstBuilder().buildFromCode {
                 println("test")
                 println("code")
            }
            //block.statements.clear()
            //block.statements.addAll(methods3[0].statements)


            //方式4、
            //methods4 是一个 BlockStatement对象数组 ： 方法注入, 原来的不清除，则调用原来的方法后，会调用我的代码
            //输出 msg test code
            def methods4 = new AstBuilder().buildFromCode {
                println("test")
                println("code")
            }
            //block.statements.clear()
            block.statements.addAll(methods4[0].statements)

        }
    }
}
