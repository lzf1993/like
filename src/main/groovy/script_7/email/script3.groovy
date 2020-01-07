package script_7.email

class Persons{
    def dream(String str){
        println "person str: "+str
    }
}


class Lances extends  Persons{
    def dream(String str){
        println "lance str: "+str
    }
}


//声明父类调子类，调用的是子类的方法
Persons person = new Lances()
person.dream("dream....")