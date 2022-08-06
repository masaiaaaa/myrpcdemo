package com.sai.version3.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
    private Integer id;
    private Integer useId;
    private String title;
}
