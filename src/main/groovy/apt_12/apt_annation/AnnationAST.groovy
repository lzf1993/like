package apt_12.apt_annation

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

@GroovyASTTransformation
class AnnationAST implements ASTTransformation{
/**
 *  命令如下：
 *   1、groovyc -d classses check.groovy AnnationAST.groovy
 *   2、jar -cf test.jar -C classes .                    //使用注解后，不需要 resources 文件夹
 *   3、groovy -classpath test.jar UseAnno.groovy        //执行
 *
 * @param astNodes 数组包括： AnnotationNode(使用的注解) 和 MethodNode数组(被注解声明的方法，比如fun方法)
 * @param sourceUnit
 */
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {

        //查找 MethodNode ， methodNodes 是一个 list
        def methodNodes = astNodes.findAll {
            it instanceof MethodNode
        }
        println "astNodes "+astNodes
        println "sourceUnit "+sourceUnit
        println "\n"
        //遍历
        methodNodes.each {
            MethodNode node ->
                //创建代码： def start = System.nanoTime()
                def startStatement = new AstBuilder().buildFromCode {
                    def start = System.nanoTime()
                }
                //创建代码：def use = System.nanoTime()-start / println("use:${use/1.0e9}")
                def endStatement = new AstBuilder().buildFromCode {
                    def use = System.nanoTime()-start
                    println("use:${use/1.0e9}")
                }
                //结点
                BlockStatement blockStatement = node.code


                println "startStatement [ "+startStatement[0] + " ] \n"
                println "endStatement [ "+endStatement[0] + " ] \n"
                println "blockStatement [ "+blockStatement + " ] \n"


                //添加节点
                ReturnStatement returnStatement = startStatement[0].statements[0]
                //添加一个表达式 -- 代码
                blockStatement.statements.add(0,new ExpressionStatement(returnStatement.expression))
                //添加多个表达式 --- 多行代码
                blockStatement.statements.addAll(endStatement[0].statements)




        }
    }
}
