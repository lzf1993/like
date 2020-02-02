package json_xml_swing_build_13.builde

import groovy.xml.MarkupBuilder
import groovy.xml.Namespace
import groovy.xml.StreamingMarkupBuilder


//============================1、xml 生成器  AndroidManifest.xml ==========================
println("\n xml 生成器: ")

//1、通过 "" 构建xml
println("\n=============== 通过 '' 构建xml ================")
def xml = """<html>
      <head m='a'>hello</head>
      <body>
           world
      </body>
   </html>
"""
println (xml.toString())


//2、通过 MarkupBuilder 生成 xml
println("\n=============== 通过 MarkupBuilder 生成xml ================")
new File("file/baidu.html").write("https://www.baidu.com/".toURL().text)
//将生成的 xml 保存在 normal.xml 中
def sw = new FileWriter("file/normal.xml")
def builder = new MarkupBuilder(sw)
builder.html{
    //添加注释
    mkp.comment("测试")
    //添加<head m='a'>hello</head>
    head("hello",m:'a'){
        title("groovy",t:'b')
    }
    //添加<body></body>
    body{
        //添加<content>task</content>
       content("task"){

       }
        //添加<foot></foot>
       foot{

       }
    }
}


// 3、同 StreamMarkupBuilder
println("\n=============== 通过 StreamMarkupBuilder 生成xml ================")
def sb = new StreamingMarkupBuilder()
sb.encoding = "UTF-8"
def closure= {
    //生成xml版本和标识
    mkp.xmlDeclaration();
    html {
        //添加注释
        mkp.comment("测试")
        //添加<head m='a'>hello</head>
        head("hello", m: 'a') {
            title("groovy", t: 'b')
        }
        //添加<body></body>
        body {
            //添加<content>task</content>
            content("task") {

            }
            //添加<foot></foot>
            foot {

            }
        }
    }
}
def st = sb.bind(closure)
println (st.toString())



//============================2、xml 解析器 ==========================

println("\n xml 解析器: ")


//1、使用 XmlParser 解析 xml
println("\n=============== XmlParser 解析 xml ================")
//(1)、解析文件
def parser = new XmlParser().parse(new File("file/AndroidManifest.xml"))
//(2)、创建命名空间，获取属性值需要 ： 命名空间.属性名称 来获取
def ns = new Namespace('http://schemas.android.com/apk/res/android','android')

//获取application节点
Node application = parser.application[0]
println("\n获取application: ")
println application

//获取activity节点
//activity[
//           attributes={
//             {http://schemas.android.com/apk/res/android}name=com.baidu.mobads.AppActivity,
//             {http://schemas.android.com/apk/res/android}configChanges=keyboard|keyboardHidden|orientation,
//             {http://schemas.android.com/apk/res/android}theme=@android:style/Theme.Translucent.NoTitleBar
//           };
//           value=[]
//        ]
Node activity = parser.application[0].activity[0]
println("\n获取第一个activity: ")
println activity

//命名空间.name : {http://schemas.android.com/apk/res/android}name
println("\n获取name属性名称: ")
println ns.name

//获取 命名空间.name 属性：com.baidu.mobads.AppActivity
println("\n获取name属性值: ")
println activity.attributes()[ns.name]

//获取 命名空间.configChanges 属性：keyboard|keyboardHidden|orientation
println("\n获取configChanges属性值: ")
println activity.attributes()[ns.configChanges]

//修改属性的值 获取 node."meta-data"是个list
Node meta = application."meta-data"[0]
//先移除当前节点 "meta-data"[0]节点
application.remove(meta)
application.appendNode('meta-data',[(ns.name):'a',(ns.value):'b',(ns.hh):'h'])
//生成一个新的xml文件
new XmlNodePrinter(new PrintWriter(new File("file/replace.xml"))).print(parser);
