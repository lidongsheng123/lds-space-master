package com.bmsoft.rmi.client.rmi.loadbalance;

import java.util.List;



public interface LoadBanalce {

    String selectHost(List<String> repos);
}


