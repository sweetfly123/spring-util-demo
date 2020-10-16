package com.example.rabbitmq.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author YunFengLiu
 */
@Data
public class RabbitMqMsgDto implements Serializable {
   private String msgBody;
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
   private Date   receiveDate = new Date();

}
