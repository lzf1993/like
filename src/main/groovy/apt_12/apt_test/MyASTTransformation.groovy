package apt_12.apt_test

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GroovyClassVisitor
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.PropertyNode
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 *  运行编译的时候，transformation 添加到classpath 就能在 自定义的 实现 ASTTransformation
 *  类当前的 visit获取 所有编译的节点，代码
 *
 *   1、编译,生成classes类   groovyc -d classes MyASTTransformation.groovy
 *   2、打包,生成 test.jar   jar -cf test.jar -C classes . -C resources .
 *   3、执行，               groovy -classpath test.jar Test.groovy
 */
@GroovyASTTransformation
class MyASTTransformation implements ASTTransformation{
/**
 *
 * @param astNodes ast抽象语法树节点
 * @param sourceUnit 源单元
 */
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        println "1111"
        //ModuleNode 对象数组
        println(astNodes)
        //ModuleNode 对象
        println(sourceUnit.AST)
        //源文件
        println(sourceUnit.source.reader.text)
        //分析文件
        sourceUnit.AST.classes.each {
            it.visitContents(new GroovyClassVisitor() {

                //分析类
                @Override
                void visitClass(ClassNode classNode) {

                }

                //分析构造方法
                @Override
                void visitConstructor(ConstructorNode constructorNode) {

                }

                //分析方法
                @Override
                void visitMethod(MethodNode methodNode) {
                    if(methodNode.name.length() == 1){
                        println "method is to short"
                    }
                }

                //分析属性
                @Override
                void visitField(FieldNode fieldNode) {

                }

                //
                @Override
                void visitProperty(PropertyNode propertyNode) {

                }
            })


        }
    }
}
