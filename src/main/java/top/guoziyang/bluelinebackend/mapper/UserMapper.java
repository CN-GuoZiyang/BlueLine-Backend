package top.guoziyang.bluelinebackend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.guoziyang.bluelinebackend.entity.User;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findUserById(Integer id);

    @Insert("INSERT INTO user VALUES(#{id},#{username},#{password},#{role},#{enable})")
    Integer insertUser(User user);
}
