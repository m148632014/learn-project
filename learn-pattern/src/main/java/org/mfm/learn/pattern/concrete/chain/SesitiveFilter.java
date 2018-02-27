package org.mfm.learn.pattern.concrete.chain;

public class SesitiveFilter implements Filter {
    public void doFilter(Request request, Response response, FilterChain fc) {
        String r = request.getRequestStr().replace("偷盗", "和谐")
                .replace("抢劫", "帮助");
        request.setRequestStr(r + "----SesitiveFilter");

        fc.doFilter(request, response, fc);

        r = response.getResponseStr() + "----SesitiveFilter";
        response.setResponseStr(r);
    }

}
