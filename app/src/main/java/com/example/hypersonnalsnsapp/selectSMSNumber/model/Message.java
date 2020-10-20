package com.example.hypersonnalsnsapp.selectSMSNumber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    public String messageId;
    public String threadId;
    public String address; //휴대폰번호
    public String contactId;
    public String contactId_string;
    public long timeStamp; //시간
    public String body; //문자내용


}
