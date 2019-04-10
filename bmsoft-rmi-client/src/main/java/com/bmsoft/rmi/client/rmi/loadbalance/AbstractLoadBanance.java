package com.bmsoft.rmi.client.rmi.loadbalance;

import java.util.List;


public abstract class AbstractLoadBanance implements LoadBanalce{

    @Override
    public String selectHost(List<String> repos) {
        if(repos==null||repos.size()==0){
            return null;
        }
        if(repos.size()==1){
            return repos.get(0);
        }
        return doSelect(repos);
    }

    protected  abstract String doSelect(List<String> repos);
}
