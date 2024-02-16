package com.example.GitRepoListApp.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchJSON {

    public String name;
    public Commit commit;
    @JsonProperty("protected")
    public boolean myprotected;
}
