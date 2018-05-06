package com.mmall.service;

import com.mmall.common.ServerResponse;

/**
 * @author Created by bameirilyo
 * @date 18-5-6 下午4:43
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);
}
