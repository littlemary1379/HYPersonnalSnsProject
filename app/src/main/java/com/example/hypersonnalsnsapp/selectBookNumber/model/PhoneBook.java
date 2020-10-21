package com.example.hypersonnalsnsapp.selectBookNumber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneBook {

    private String id;
    private String name;
    private String phoneNumber;

}
