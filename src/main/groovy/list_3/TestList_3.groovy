package list_3

//第三部分： list相关操作


//数组
public void test(){
    println " ------  数组定义 ------  ："
    def list1 = [1,2,3,"4ff"];   //快捷创建数组
    list1 as LinkedList;         //数组类型
    println list1[-1];           //最后一位开始取值
    println list1[3];            //根据下标取值
    println list1[0..2];         //获取数组某个区间内容，内容是个数组

    list1[1] = 1000;             //进行赋值
    def list2 = list1[0..1];     //获取数组 0,1两个元素，返回的是一个新的list
    list2 = 2000;                //修改list2的值，但不影响list1的值
    println "list1: "+list1;
    println "list2: "+list2;
}


//数组循环
public void test2(){
    println " \n------  数组循环 ------  ："

    def list = [11,22,22,22,33,44,55,66,77,88,99];

    println "java普通循环: "
    for(i in list){
        print i+" ";
    }
    println "\n------------------------ "


    //1、只是单纯的循环而已，返回的是原来集合本身
    //Iterator<T> each(Iterator<T> iter, Closure closure)
    println "groovy each循环: "
    println "原数组： "+list;
    print "元素： "
    def list2 = list.each {
        it++ ;
        print it+" "
        return it;
    }
    println "\n循环后返回的数组: "+list2;
    println "原数组： "+list;
    println "\n------------------------ "


    //2、除了循环，还返回闭包执行后的集合
    println "groovy collect循环: "
    println "原数组： "+list;
    print "元素： "
    def list3 = list.collect {
        it = it+100;
        print it+" "
        return it;  //如果没有这一行，则会把  print it+" " 当成返回值，返回为null, 这样最后循环完成的数组就是个空数组
    }
    println "\n循环后返回的数组： "+list3;
    println "原数组： "+list;
    println "\n------------------------ "


    //3、循环获取元素和下标的循环
    println "反向循环，获取元素和下标: "
    println "原数组： "+list;
    print "元素： "
    list.eachWithIndex { Object entry, int i ->
        println("  data -> "+entry+"  index -> "+i);
    }
    println "\n------------------------ "


    //4、反向循环
    // <T> List<T> reverseEach(List<T> self, @ClosureParams(FirstParam.FirstGenericType.class) Closure closure)
    println("反向循环 ：")
    println "原数组： "+list;
    print "元素： "
    list.reverseEach {
        print(" i-> "+it+"  ");
    }
    println "\n------------------------ "


    //5、查找某个元素，返回的是这个元素，当找到第一个满足条件元素就返回，就不再查找
    println("查找某个元素 ：")
    println "原数组： "+list;
    def a =list.find({
        it == 22;   //相当于equals
    })
    print("a: "+a);
    println "\n------------------------ "


    //6、查找某个元素、返回的是查找结果数组，这个元素有可能有多个
    println("查找某个元素，返回数组 ：")
    println "原数组： "+list;
    def ls = list.findAll({
        it == 22;   //相当于equals
    })
    print("ls: "+ls);
    println "\n------------------------ "


    // 相当于 ==
    println("判断是否是 ==  ：")
    def d = 2.is(3)
    println("d : "+d);


    println "\n------------------------ "
}



//操作符重载
void test3(){
    println " ------  数组操作 ------  ："

    def list = [22,33,44,55,66,77,88,99];

    //1、集合增加一个元素
    println("原数组 : "+list)
    print "+添加一个元素 "
    println list + 100;     //添加一个元素
    print "-删除一个元素 "
    println list - 99 ;     //删除一个元素
    println "\n------------------------ "


    //<<左移删除元素
    println("原数组 : "+list)
    print "<< 添加元素："
    println  list << [1000,10001];   //集合里面再添加一个集合元素 : [22, 33, 44, 55, 66, 77, 88, 99, [1000, 10001]]  , 不支持 >>
    println  list.flatten()          //进行拉平，变成 : [22, 33, 44, 55, 66, 77, 88, 99, 1000, 10001]
    println "\n------------------------ "


    //特殊操作符
    def list2 = ["i", "love", "java"];
    println "使用展开操作符："
    println("原字符数组 : "+list2)
    print "展开操作符后数组： "
    println list2*.toUpperCase()    //拿到里面的每一个元素，并调用 toUpperCase方法， 变成大写
    println "\n------------------------ "


    //查找操作，满足返回true, 不满足返回false
    println "any操作符："
    print list2.any({
        it == "java"   //查找是否存在 “java” 元素
    })
    println "\n------------------------ "


    //查找操作，是否所有的元素都包含改字符
    println "every操作符："
    def list3 = ["i1", "love1", "java1"];
    print list3.every({    //是否所以的元素都包括1
        it =~ "1"  //==~ 精准匹配

    })
    println "\n------------------------ "


}



test();
test2();
test3();