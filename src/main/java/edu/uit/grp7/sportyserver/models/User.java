package edu.uit.grp7.sportyserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String accountId;

    private String fullName;
    private Integer height;
    private Float weight;
    private Boolean gender;

    private List<Test> testResults;


}
