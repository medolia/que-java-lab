# google java style guideline

## 6 编码

## 6.1

## 6.2 异常捕获：不要无视

总是要捕获异常，一般是打个日志，或是再抛出这个异常。

当有确实不需要处理的异常的时候，也需要加上一段注释。

```java
try{
      int i=Integer.parseInt(response);
      return handleNumericResponse(i);
      }catch(NumberFormatException ok){
      // it's not numeric; that's fine, just continue
      }
      return handleTextResponse(response);
```

