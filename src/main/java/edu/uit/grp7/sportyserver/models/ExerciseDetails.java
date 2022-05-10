package edu.uit.grp7.sportyserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ExerciseDetails {
    private Integer set;
    private Integer rep;
}
