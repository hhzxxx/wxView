package com.qxt.bysj.service;

import com.qxt.bysj.domain.Product;
import java.util.List;
import java.util.Map;

public interface ProductService extends BaseService<Product>{

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> findPage(Map<String, Object> map);
}
