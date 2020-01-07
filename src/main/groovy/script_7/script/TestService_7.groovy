package script_7.script

//线程流
def server = new ServerSocket(9999);

//创建普通的线程
def isRunning = true;
def sockets = [];

//关闭socket
def close(socket){
    if(null != socket) {
        if (!socket.isClosed()) {
            socket.close()
        }
    }
}


//开启线程
Thread.start {
    println "开启服务器....."
   while(isRunning){
       try {
           //获取连接，保存到数组里面
           def socket = server.accept();
           sockets << socket;
           println "新的连接.....${socket.remoteSocketAddress}"
           Thread.start {
               //读取内容
               while(!socket.isClosed()){
                   try{
                       def buffer =  new byte[2048]
                       def len = socket.inputStream.read(buffer)
                       if(len>0){
                           println "[${socket.remoteSocketAddress}]: ${new String(buffer,0,len)}]"
                       }
                   }catch(ex){
                       println "异常："+${ex.getLocalizedMessage()}
                   }
               }
               println "断开连接: [${socket.remoteSocketAddress}]"
           }
       }catch(ex){
           //关闭连接
           close(sockets)
           println "异常："+${ex.getLocalizedMessage()}
       }
   }
    println "关闭服务器......"
    close(server);
    //循环关闭连接
    sockets.each {
        close(it)
    }
}

//读取输入的内容
def scanner = new Scanner(System.in)
Thread.start {
    while (isRunning){
        def msg = scanner.nextLine();
        if(msg == 'exit'){
            isRunning = false
            close(server)
            sockets.each {
                close(it)
            }
            break
        }
        //获取数组中的每一个socket，调用输出流
        sockets*.outputStream.with {msg.getBytes()}
    }
}



//创建守护线程
Thread.startDaemon {

}








