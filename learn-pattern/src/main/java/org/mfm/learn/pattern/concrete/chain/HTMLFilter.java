package org.mfm.learn.pattern.concrete.chain;

public class HTMLFilter implements Filter {

    public void doFilter(Request request, Response response,FilterChain fc) {
        //先处理带尖括号的信息
        String str=request.getRequestStr().replace('<', '[')
                .replace('>', ']');
        request.setRequestStr(str+"----HTMLFilter");


        fc.doFilter(request, response, fc);

        str=response.getResponseStr()+"----HTMLFilter";
        response.setResponseStr(str);
    }


}
