json、xml、swing、build生成器：


1、构建json

    JsonBuilder：

      public class JsonBuilder extends GroovyObjectSupport implements Writable {

         public Object invokeMethod(String name, Object args) {
              if (args != null && Object[].class.isAssignableFrom(args.getClass())) {
                  Object[] arr = (Object[]) args;
                  if (arr.length == 0) {
                      return setAndGetContent(name, new HashMap<String, Object>());
                  } else if (arr.length == 1) {
                      if (arr[0] instanceof Closure) {
                          return setAndGetContent(name, JsonDelegate.cloneDelegateAndGetContent((Closure) arr[0]));
                      } else if (arr[0] instanceof Map) {
                          return setAndGetContent(name, arr[0]);
                      }
                  } else if (arr.length == 2)  {
                        if (arr[0] instanceof Map && arr[1] instanceof Closure) {
                            Map subMap = new LinkedHashMap();
                            subMap.putAll((Map) arr[0]);
                            subMap.putAll(JsonDelegate.cloneDelegateAndGetContent((Closure) arr[1]));
                            return setAndGetContent(name, subMap);
                        } else if (arr[0] instanceof Collection && arr[1] instanceof Closure) {
                            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                            for (Object it : (Collection) arr[0]) {
                                list.add(JsonDelegate.curryDelegateAndGetContent((Closure) arr[1], it));
                            }
                            return setAndGetContent(name, list);
                        }
                    }
                  throw new JsonException("Expected no arguments, a single map, a single closure, or a map and closure as arguments.");
              } else {
                  return setAndGetContent(name, new HashMap<String, Object>());
              }
         }
      }

  2、构建xml:
      MarkupBuilder
      StreamingMarkupBuilder

      public class MarkupBuilder extends BuilderSupport {

      }

      class StreamingMarkupBuilder extends AbstractStreamingBuilder {

      }