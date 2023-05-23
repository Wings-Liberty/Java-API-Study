package com.cx.bean;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User>{

    private Long id;

    private String name;

    private String password;

    private Dog dog;

    @Override
    public int compareTo(User o) {
        if(ObjectUtil.isEmpty(o)){
            return 1;
        }
        return StrUtil.emptyIfNull(this.name).length() - StrUtil.emptyIfNull(o.name).length();
    }
}
