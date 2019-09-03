package org.apache.dubbo.rpc;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;

@Adaptive
public class Protocol$Adaptive implements org.apache.dubbo.rpc.Protocol {

    //NOTE: destory()和getDefaultPort() 都没有@Adaptive注解，所以不会创建具体实现
    public void destroy() {
        throw new UnsupportedOperationException(
                "method public abstract void org.apache.dubbo.rpc.Protocol.destroy() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    public int getDefaultPort() {
        throw new UnsupportedOperationException(
                "method public abstract int org.apache.dubbo.rpc.Protocol.getDefaultPort() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!");
    }

    /**
     * 服务暴露
     * @param arg0
     * @return
     * @throws org.apache.dubbo.rpc.RpcException
     */
    public org.apache.dubbo.rpc.Exporter export(org.apache.dubbo.rpc.Invoker arg0) throws org.apache.dubbo.rpc.RpcException {
        if (arg0 == null) {
            throw new IllegalArgumentException(
                    "org.apache.dubbo.rpc.Invoker argument == null");
        }

        if (arg0.getUrl() == null) {
            throw new IllegalArgumentException(
                    "org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        }

        //NOTE: 根据URL的参数来确定具体要调用哪个Protocol的扩展实现类
        org.apache.dubbo.common.URL url = arg0.getUrl();
        String extName = ((url.getProtocol() == null) ? "dubbo" : url.getProtocol());

        if (extName == null) {
            throw new IllegalStateException(
                    "Fail to get extension(org.apache.dubbo.rpc.Protocol) name from url(" +
                            url.toString() + ") use keys([protocol])");
        }

        //NOTE: 获得对应的Protocol的扩展实现类
        org.apache.dubbo.rpc.Protocol extension = (org.apache.dubbo.rpc.Protocol) ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        //NOTE：服务暴露
        return extension.export(arg0);
    }

    /**
     * 服务引用
     * @param arg0
     * @param arg1
     * @return
     * @throws org.apache.dubbo.rpc.RpcException
     */
    public org.apache.dubbo.rpc.Invoker refer(java.lang.Class arg0,
                                              org.apache.dubbo.common.URL arg1) throws org.apache.dubbo.rpc.RpcException {
        if (arg1 == null) {
            throw new IllegalArgumentException("url == null");
        }

        //NOTE: 根据URL的参数来确定具体要调用哪个Protocol的扩展实现类
        org.apache.dubbo.common.URL url = arg1;
        String extName = ((url.getProtocol() == null) ? "dubbo" : url.getProtocol());

        if (extName == null) {
            throw new IllegalStateException(
                    "Fail to get extension(org.apache.dubbo.rpc.Protocol) name from url(" +
                            url.toString() + ") use keys([protocol])");
        }

        org.apache.dubbo.rpc.Protocol extension = (org.apache.dubbo.rpc.Protocol) ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);
        //引用服务
        return extension.refer(arg0, arg1);
    }
}
