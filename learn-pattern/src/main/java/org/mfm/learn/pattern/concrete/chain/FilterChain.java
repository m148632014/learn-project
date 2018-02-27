package org.mfm.learn.pattern.concrete.chain;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter{
    List<Filter> filters=new ArrayList<Filter>();
    //记录运行到哪一个Filter了
    int index=0;

    //返回值是FilterChain只为了在Main中满足链条式的编程
    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    public void doFilter(Request request,Response response,FilterChain fc) {
        if(index==filters.size()) return;

        Filter f=filters.get(index);
        index++;
        f.doFilter(request, response, fc);

    }
}