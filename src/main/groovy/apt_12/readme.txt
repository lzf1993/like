

  编译时元编程：

         1、实现 ASTTransformation， 添加 @GroovyASTTransformation 注解

         @GroovyASTTransformation
         class MyASTTransformation implements ASTTransformation{
             @Override
             void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {

             }
         }

         关键类：
            astNodes: 是一个 ModuleNode 数组
            sourceUnit：
            ModuleNode：这里面包括类的信息，方法的信息，接口的信息等
                    BlockStatement statementBlock;
                    //类信息
                    List<ClassNode> classes ;
                    //方法信息
                    List<MethodNode> methods;
                    Map<String, ImportNode> imports ;
                    List<ImportNode> starImports;
                    Map<String, ImportNode> staticImports ;
                    Map<String, ImportNode> staticStarImports;
                    CompileUnit unit;
                    PackageNode packageNode;
                    String description;
                    boolean createClassForStatements = true;
                    transient SourceUnit context;
                    boolean importsResolved = false;
                    ClassNode scriptDummy;
                    String mainClassName = null;
                    
             ClassNode：类节点
                     String name;
                     int modifiers;
                     boolean syntheticPublic;
                     ClassNode[] interfaces;
                     MixinNode[] mixins;
                     List<ConstructorNode> constructors;
                     //表达式节点
                     List<Statement> objectInitializers;
                     List<InnerClassNode> innerClasses;
                     //方法节点
                     List<MethodNode> methodsList;
                     LinkedList<FieldNode> fields;
                     List<PropertyNode> properties;
                     Map<String, FieldNode> fieldIndex;
                     ModuleNode module;
                     CompileUnit compileUnit;
                     boolean staticClass = false;
                     boolean scriptBody = false;
                     boolean script;
                     ClassNode superClass;
                     boolean isPrimaryNode;
                     MapOfLists methods;
                     
             MethodNode：方法节点
                     final String name;
                     int modifiers;
                     boolean syntheticPublic;
                     ClassNode returnType;
                     Parameter[] parameters;
                     boolean hasDefaultValue = false;
                     //代码节点 ==》 BlockStatement类型
                     Statement code;
                     boolean dynamicReturnType;
                     VariableScope variableScope;
                     final ClassNode[] exceptions;
                     final boolean staticConstructor;
                     GenericsType[] genericsTypes = null;

             BlockStatement:代码节点
                    //有： ExpressionStatement、ReturnStatement、CaseStatement等
                    List<Statement> statements;

             ExpressionStatement: 代码表达式节点
