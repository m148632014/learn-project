package org.mfm.learn.pattern.concrete.chain;

public interface Filter {
    void doFilter(Request request,Response response,FilterChain fc);
}