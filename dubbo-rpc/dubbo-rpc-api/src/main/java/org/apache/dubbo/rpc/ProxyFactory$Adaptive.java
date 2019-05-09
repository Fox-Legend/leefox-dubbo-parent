package org.apache.dubbo.rpc;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * @author huoyu.
 * @date 2019/4/7.
 */
@Adaptive
public class ProxyFactory$Adaptive implements ProxyFactory {

    /**
     * 创建Invoker，服务暴露
     */
    public Invoker getInvoker(Object arg0, Class arg1, org.apache.dubbo.common.URL arg2) throws RpcException {
        if (arg2 == null) {
            throw new IllegalArgumentException("url == null");
        }

        org.apache.dubbo.common.URL url = arg2;
        String extName = url.getParameter("proxy", "javassist");
        if (extName == null) {
            throw new IllegalStateException("Fail to get extension(org.apache.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        }

        ProxyFactory extension = (ProxyFactory) ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);

        return extension.getInvoker(arg0, arg1, arg2);
    }

    public Object getProxy(Invoker arg0, boolean arg1) throws RpcException {
        if (arg0 == null) {
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument == null");
        }

        if (arg0.getUrl() == null) {
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        }

        org.apache.dubbo.common.URL url = arg0.getUrl();
        String extName = url.getParameter("proxy", "javassist");

        if (extName == null) {
            throw new IllegalStateException("Fail to get extension(org.apache.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        }

        ProxyFactory extension = (ProxyFactory) ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);

        return extension.getProxy(arg0, arg1);
    }

    /**
     * 获取Invoker的代理
     * @param arg0
     * @return
     * @throws RpcException
     */
    public Object getProxy(Invoker arg0)
            throws RpcException {
        if (arg0 == null) {
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument == null");
        }

        if (arg0.getUrl() == null) {
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        }

        org.apache.dubbo.common.URL url = arg0.getUrl();
        //获取代理，默认为javassist
        String extName = url.getParameter("proxy", "javassist");

        if (extName == null) {
            throw new IllegalStateException("Fail to get extension(org.apache.dubbo.rpc.ProxyFactory) name from url(" + url.toString() + ") use keys([proxy])");
        }

        ProxyFactory extension = (ProxyFactory) ExtensionLoader.getExtensionLoader(ProxyFactory.class).getExtension(extName);

        return extension.getProxy(arg0);
    }


}
