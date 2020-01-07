package script_7.email

import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.MimeMessage

//运行在服务器上，给所有相关人员发送邮件


args = []
args[0] = 'email.groovy'

def conf = new ConfigSlurper().parse(new File(args[0]).toURI().toURL())
//读取到地址
println conf.mail.smtp.host
println conf.mail.env.name
println conf.mail.env.pwd

//邮箱会话， 设置密码
def session = Session.getDefaultInstance(conf.toProperties(),new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(conf.mail.env.name,conf.mail.env.pwd)
    }
})
session.setDebug(true)

//邮件
def msg = new MimeMessage(session)
//设置发件人
msg.setFrom(conf.mail.env.name)
//设置收件人: To 简单收件人  BC:抄送  CC:密送
msg.setRecipients(Message.RecipientType.TO, conf.mail.env.tolist)
//设置主题
msg.setSubject("ip notify ")
//设置正文
def address = InetAddress.localHost  //获取ip
msg.setText(address.hostName+" ip change: "+address.hostAddress+" 老铁我的脚本发的 ")

//发送邮件
Transport.send(msg)



//一、运行脚本
//1、 linux上运行脚本:

// /user/bin/env groovy -classpath javax.mail.jar  把jar包和源码放在一个包

//2、window上运行脚本:

//源码目录下  groovy -classpath javax.mail.jar script.groovy.email.groovy



//二、一个脚本调用另一个脚本








