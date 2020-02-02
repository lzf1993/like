package json_xml_swing_build_13

import json_xml_swing_build_13.builde.MyXmlBuilderSupport



//自定义 xml 解析器

StringWriter writer = new StringWriter()
def builder = new MyXmlBuilderSupport(writer)
//创建完 html 调 == createNode();
builder.html{
    //创建完 head 调 == createNode() ， 发现父节点是 html, 然后调了 ==  setParent()
    //value ==> attrs
    head('动脑学院',wife:'wyy'){
      content('内容',time:'today'){
          foot('底部', head:'测试'){

          }
      }
    }
    //创建完 body 调 == createNode() ， 发现父节点是 html, 然后调了 ==  setParent()
    body{

    }
}

println("\n自定义builder: ")
println(writer)