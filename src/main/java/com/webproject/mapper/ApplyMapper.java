package com.webproject.mapper;

import com.webproject.entity.Apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
    @Insert("insert into apply(uid, aname, message,audit) " +
            "values(#{uid}, #{aname}, #{message},#{audit}) ")
    void addApply(int uid, String aname, String message, int audit);
    @Update("update apply set audit = #{audit} where uid = #{uid} ORDER BY time DESC LIMIT 1")
    void setAudit(int audit, int uid);
    @Select("select * from apply where uid = #{uid}")
    Apply selectApplyByUid(int uid);
    @Select("select * from apply where aname = #{aname}")
    Apply selectApplyByName(String aname);
    @Select("select distinct " +
            "  u.uid," +
            "  u.username," +
            "  a.aname," +
            "  u.avatar," +
            "  a.audit," +
            "  a.message," +
            "  a.time " +
            " FROM " +
            "  user u" +
            " JOIN " +
            "  apply a ON u.uid = a.uid " +
            "where (u.username like concat('%', #{likename}, '%') or " +
            "a.aname like concat('%', #{likename}, '%')) " +
            "and a.audit like concat('%', #{audit}, '%')")
    List<Map<String,Object>> likeSelect(String likename, String audit);

}
