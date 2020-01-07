package apt_12.apt_annation;


import org.codehaus.groovy.transform.GroovyASTTransformationClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 创建一个注解，作用在方法上
 */
@Target(ElementType.METHOD)
@GroovyASTTransformationClass('apt_12.apt_annation.AnnationAST')
@interface Check {
}
