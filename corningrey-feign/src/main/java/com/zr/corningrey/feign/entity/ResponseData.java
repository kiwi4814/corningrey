package com.zr.corningrey.feign.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseData<T> {

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String message;

    /**
     * 信息状态码
     */
    @ApiModelProperty(value = "信息状态码")
    private String messageCode;

    /**
     * 结果状态码
     */
    @ApiModelProperty(value = "结果状态码，1为成功，2为失败")
    private String resultCode;

    /**
     * 数据体
     */
    @ApiModelProperty(value = "数据体")
    private T data;
}
