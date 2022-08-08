package com.sai.version4.server;

import com.sai.version4.common.Blog;
import com.sai.version4.service.BlogService;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:33
 */
public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").useId(22).build();
        System.out.println("客户端查询了"+id+"博客");
        return blog;
    }
}
