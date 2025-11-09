package org.example.projecttracker.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectTracker
{
    private Integer ID;
    private String title;
    private String description;
    private String status;
    private String companyName;
}
