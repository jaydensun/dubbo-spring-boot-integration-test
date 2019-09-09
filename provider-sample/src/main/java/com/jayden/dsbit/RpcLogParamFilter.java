package com.jayden.dsbit;

import com.google.gson.Gson;
import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 打印RPC上行以及下行参数信息
 *
 * @author 698533
 */
@Activate(group = Constants.PROVIDER, order = -88888888)
public class RpcLogParamFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RpcLogParamFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String path = invoker.getUrl().getPath() + "-" + invocation.getMethodName();
        //采用path中是否含有"/"判断是否为http（方案暂时不是非常严谨,公司内部都会配置contextpath）
        if (path.contains("/")) {
            return invoker.invoke(invocation);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The RPC(" + path + ") info:" + "\n" + "Req body is :\n");
        Object obj[] = invocation.getArguments();
        for (int i = 0; i < obj.length; i++) {
            sb.append("params" + (i + 1) + ": " + obj[i] + "\n");
        }

        Result rst = invoker.invoke(invocation);
        try {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(rst.getValue());
            sb.append("Rsp body is :\n");
            sb.append(jsonStr);
        } catch (Exception e) {
            //异常可以忽略
        }
        logger.info(sb.toString());
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "hello : ");
        map.put("class", "com.jayden.dsbit.HelloResponse");
//        return new RpcResult(map);
        return rst;
    }
}
