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
public class PracticeGroup {
    private String groupId;
    private String groupName;

    private List<User> groupMembers;
    private List<Exercise> practiceExercises;
    private Integer duration;
}
