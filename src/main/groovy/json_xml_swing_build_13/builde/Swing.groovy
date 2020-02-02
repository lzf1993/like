package json_xml_swing_build_13.builde

import groovy.swing.SwingBuilder

import javax.swing.WindowConstants
import java.awt.FlowLayout


//============================1、swing ==========================


//创建一个 swing窗口
def builder = new SwingBuilder()
def swing = builder.frame(title:'测试',size:[200,200],layout: new FlowLayout(),defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE){
    label(text:'文本')
    button(text:'按钮',actionPerformed:{
         print('点击')
    })
}
swing.setVisible(true)


