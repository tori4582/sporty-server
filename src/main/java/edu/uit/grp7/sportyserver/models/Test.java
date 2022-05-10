package edu.uit.grp7.sportyserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Test {
    private String testId;
    private String pushUp;
    private Integer crunch;
    private Integer squat;
    private Integer pullUp;
    private Integer planks;
}
