package com.mytest.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface Site {
    @Select("select * from site where id = #{id}")
    public com.mytest.domains.Site getSiteById(long id);

    @Insert("insert into site(name,url) values (#{name}, #{url})")
    public int insertSite(com.mytest.domains.Site site);
}
