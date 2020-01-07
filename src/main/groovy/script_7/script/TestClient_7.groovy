package script_7.script

def client = new Socket("localhost",9999)



Thread.start {
    println "开始连接服务器...."
    while(!client.isClosed()){
        try {
            def buffer =  new byte[2048]
            def len = client.inputStream.read(buffer)
            if(len>0){
                println "[${client.remoteSocketAddress}]: ${new String(buffer,0,len)}]"
            }
        }catch(ex){
            println "异常："+${ex.getLocalizedMessage()}
        }
    }

}

//读取内容
def scanner = new Scanner(System.in)
Thread.start {
    while(!client.isClosed()){
        def msg = scanner.nextLine();
        if(msg == 'exit'){
            client.close()
            break
        }
        client.outputStream.with {msg.getBytes()}
    }
}