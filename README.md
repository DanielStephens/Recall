# Recall
A proxy based framework, used to 'record' methods called against an object, and providing the ability to the replay these same actions at a later poin in time.

This is done through using CG library proxies to catch method invocations; save these invocations for 'replaying' later and then building and returning 'useful' default values based on the expected method return value. These defaults allow for nested recording.

## Nesting example
```java
AnyRef proxy = Recall.enhance(AnyRef);
AnyRef nestedProxy = proxy.getAnyRef();
nestedProxy.doSomething();
```


