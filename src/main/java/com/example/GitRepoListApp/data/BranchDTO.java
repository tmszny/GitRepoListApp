package com.example.GitRepoListApp.data;

import lombok.Data;

@Data
public class BranchDTO {

    private String name;
    private String sha;

    public BranchDTO(String name, String sha) {
        this.name = name;
        this.sha = sha;
    }
}
