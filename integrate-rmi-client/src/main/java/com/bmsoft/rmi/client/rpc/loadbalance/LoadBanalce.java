package com.bmsoft.rmi.client.rpc.loadbalance;

import java.util.List;



public interface LoadBanalce {

    String selectHost(List<String> repos);
}


