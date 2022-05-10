package edu.uit.grp7.sportyserver.models;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Exercise {

    private String exerciseId;
    private String exerciseName;

    private Integer level;
    private Integer exerciseType;
    private String description;
    private String imageUrl;

    private ExerciseDetails details;


}
