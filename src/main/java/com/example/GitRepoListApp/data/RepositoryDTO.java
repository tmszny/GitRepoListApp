package com.example.GitRepoListApp.data;

import lombok.Data;

import java.util.List;

@Data
public class RepositoryDTO {

    private String name;
    private String login;
    private List<BranchDTO> branches;

    public RepositoryDTO(String name, String login, List<BranchDTO> branches) {
        this.name = name;
        this.login = login;
        this.branches = branches;
    }
}
