package org.mfm.learn.pattern.concrete.chain;

public class Main {
    public static void main(String[] args) {
        String msg="大家好：),<script>，偷盗，抢劫，我们要把设计模式学好";
        Request request=new Request();
        request.setRequestStr(msg);
        Response response=new Response();
        //response对象无反馈信息之前先设一个暂时的信息"response"
        response.setResponseStr("response");
        FilterChain fc=new FilterChain();
        fc.addFilter(new HTMLFilter())
                .addFilter(new SesitiveFilter());//链条式写法


        fc.doFilter(request, response,fc);
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }
}
