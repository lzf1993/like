package json_xml_swing_build_13.builde


/**
 * 自定义xml生成器
 */
class MyXmlBuilderSupport extends BuilderSupport {

    def nodes;
    Writer writer;

    MyXmlBuilderSupport(Writer writer) {
        nodes = [:]
        this.writer = writer;
    }


/**
 * 节点与节点的关系 createNode会调用了 setParent
 * @param parent 父节点
 * @param child 子节点
 */
    @Override
    protected void setParent(Object parent, Object child) {
        println "setParent == > parent: $parent  child: $child"
    }

    @Override
    protected Object createNode(Object name) {
        return createNode(name, null, null);
    }

    @Override
    protected Object createNode(Object name, Object value) {
        return createNode(name, null, value);
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        return createNode(name, attributes, null);
    }


/**
 * 节点完成调用
 * @param parent
 * @param node
 */
    @Override
    protected void nodeCompleted(Object parent, Object node) {
        super.nodeCompleted(parent, node)
        println("nodeCompleted ==> parent: $parent  node: $node")
        def currNode = nodes[node];
        if(parent){
            //将当前节点，放到父节点的 children 中
            nodes[parent].children << currNode;
        }else{
            //构建我们需要的数据
            currNode.build(writer);
        }
    }


/**
 * 创建节点时候回调， createNode会调用了 setParent
 * @param name
 * @param attributes
 * @param value
 * @return 该处的返回值会 作为 setParent 的参数
 */
    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        //保存节点
        nodes.put(name,new Node(name,value,attributes));
        println("createNode =>name: $name  value: $value  attributes:  $attributes")
        return name
    }


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

}

/*

<html>
     //name: head
     //attrs: m = 'a'
     //value: hello
     //children[] :
     //         <title t='b'>groovy</title>
     //         <content>task</content>
     <head m='a'>hello
        <title t='b'>groovy</title>
        <content>task</content>
     </head>
     <body>
        <content>task</content>
        <foot />
     </body>
</html>

 */
