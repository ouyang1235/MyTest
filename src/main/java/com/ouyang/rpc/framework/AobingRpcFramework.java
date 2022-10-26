package com.ouyang.rpc.framework;

import com.ouyang.rpc.impl.AobingServiceImpl;
import com.ouyang.rpc.interfaces.AobingService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class AobingRpcFramework {

    public static void export(Object service,int port)throws Exception{
        ServerSocket server = new ServerSocket(port);
        while (true){
            Socket socket = server.accept();
            new Thread(() -> {
                try{
                    //反序列化
                    byte[] bytes = new byte[1024];
                    ObjectInputStream input = null;
                    input = new ObjectInputStream(socket.getInputStream());
                    int read = input.read(bytes);
                    String methodName = new String(bytes);
                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                    Object[] arguments = (Object[]) input.readObject();
                    Method method = service.getClass().getMethod(methodName, parameterTypes);
                    Object result = method.invoke(service, arguments);
                    //返回结果
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    output.writeObject(result);
                }catch (Exception e){

                }
            }).start();
        }
    }

    public static <T> T refer (Class<T> interfaceClass,String host,int port) throws Exception{
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.write(method.getName().getBytes());  //传方法名
                output.writeObject(method.getParameterTypes());  //传参数类型
                output.writeObject(args);  //传参数值
                output.flush();

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Object result = input.readObject();  //读取结果
                System.out.println(result.toString());
                return result;
            }
        });

    }


    public static void main(String[] args) throws Exception {
        //服务提供者只需要暴露出接口
        AobingService service = new AobingServiceImpl();
        AobingRpcFramework.export(service, 2333);

    }


}
