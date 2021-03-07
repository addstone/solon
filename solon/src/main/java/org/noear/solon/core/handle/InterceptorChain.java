package org.noear.solon.core.handle;


import org.noear.solon.core.wrap.MethodHolder;
import org.noear.solon.core.wrap.MethodWrap;

/**
 * 方法拦截调用链（用于支持 @Around ）
 *
 * @author noear
 * @since 1.0
 * */
public interface InterceptorChain {
    /**
     * 方法容器
     */
    MethodHolder method();

    /**
     * 拦截传递
     *
     * @param target 目标对象
     * @param args   参数
     */
    Object doIntercept(Object target, Object[] args) throws Throwable;


    class Entity implements InterceptorChain {
        private final Interceptor interceptor;
        private final MethodWrap methodWrap;

        public final int index;
        public InterceptorChain next;

        public Entity(MethodWrap m, int i, Interceptor p) {
            index = i;
            interceptor = p;
            methodWrap = m;
        }

        @Override
        public MethodHolder method() {
            return methodWrap;
        }

        @Override
        public Object doIntercept(Object target, Object[] args) throws Throwable {
            return interceptor.doIntercept(target, args, next);
        }
    }
}
