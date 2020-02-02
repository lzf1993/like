package json_xml_swing_build_13.builde


/**
 * 自定义的 Swing 生成器
 */
class MySwingBuilderSupport extends FactoryBuilderSupport{
    {
        def nodeFactory = new NodeFactory()
        //注册工厂
        registerFactory('html',nodeFactory)
        registerFactory('head',nodeFactory)
    }

}




class NodeFactory extends AbstractFactory{

    def name ;

    @Override
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        println "NodeFactory ==> newInstance"
        this.name = name;
        return new Node(name,value,attributes);
    }


/**
 * 处理属性的方法
 * @param builder
 * @param node
 * @param attributes
 * @return 如果返回true, 则会从newInstance返回中寻找 attributes对应的key的属性
 */
    @Override
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
//        return super.onHandleNodeAttributes(builder, node, attributes)
        return false
    }


/**
 *false 表示可以接受闭包  ture可以接受闭包
 * @return
 */
    @Override
    boolean isLeaf() {
        return super.isLeaf()
    }


/**
 *设置父节点 === 子节点调用
 * @param builder
 * @param parent
 * @param child
 */
    @Override
    void setParent(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setParent(builder, parent, child)
        parent.children << child
        println("$name  setParent ${parent.name} ${child.name}")
    }


/**
 *设置子节点 == 父节点调用
 * @param builder
 * @param parent
 * @param child
 */
    @Override
    void setChild(FactoryBuilderSupport builder, Object parent, Object child) {
        super.setChild(builder, parent, child)
        println("$name  setChild ${parent.name} ${child.name}")
    }
}


/**
 * 结点
 */
class Node{
    //名称
    String name;
    //值
    String value;
    //属性
    Map attr;
    //子节点
    def children;
    Node(String name, String value, Map attr) {
        this.name = name
        this.value = value
        this.attr = attr
        this.children = []
    }
    //生成xml, 没有child <xml key='value'/>
    //有child <xml>xxx</xml>
    def build(Writer writer){
        writer.write("<$name")
        //拼好属性
        if(attr){
            attr.each {
                key,value ->
                    writer.write(" $key='$value'")
            }
        }
        if(value || children){
            writer.write(">")
            if(value){
                writer.write(value)
            }
            children.each{
                //如果有子节点，则子节点继续遍历
                it.build(writer);
            }
            writer.write("</$name>")
        }
        //没有子节点
        else{
            writer.write("/>")
        }
    }
}



