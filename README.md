news-kbservice 设计模式模块
 
 序列化单列模式 核心代码截图 
    ObjectInputStream ->readObject() 
      		      ->readObject0()
		       ->case TC_OBJECT:
                         checkResolve(readOrdinaryObject(unshared));
			 
    
        
