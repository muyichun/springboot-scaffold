用户-角色-权限，三表多对多联表查询！
```sql
    <select id="getUserMenu" resultType="com.xujing.springbootscaffold.modules.test.entity.Menu">
SELECT b.name, b.href FROM r_role_menu a
                               LEFT JOIN menu b ON a.`menu_id` = b.`id`
WHERE EXISTS (SELECT r.`id` FROM r_user_role r_u_r
                                     LEFT JOIN role r ON r_u_r.`role_id` = r.`id`
              WHERE r_u_r.`user_id` = #{userId} AND a.role_id = r.id)
          </select>

-- 多对多的三张表，可以做到一条sql全部查询出来
SELECT DISTINCT(b.name), b.href FROM r_role_menu a
                                         LEFT JOIN menu b ON a.`menu_id` = b.`id`
WHERE EXISTS (SELECT r.`id` FROM r_user_role r_u_r
                                     LEFT JOIN role r ON r_u_r.`role_id` = r.`id`
              WHERE r_u_r.`user_id` = 1 AND a.role_id = r.id)

```
