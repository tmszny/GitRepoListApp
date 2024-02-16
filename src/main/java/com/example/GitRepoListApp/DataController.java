package com.example.GitRepoListApp;

import com.example.GitRepoListApp.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DataController {

    private final String GITHUB_API_URL = "https://api.github.com/";
    private final RestTemplate restTemplate;

    @Autowired
    public DataController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/info/{username}")
    public ResponseEntity<?> getInfo(@PathVariable("username") String username) {
        try {
            List<RepositoryDTO> repositoryDTOList = getRepositoriesByUser(username);
            return ResponseEntity.status(HttpStatus.OK).body(repositoryDTOList);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public List<RepositoryDTO> getRepositoriesByUser(String username) {
        String url = GITHUB_API_URL + "users/"  + username + "/repos";
        RepositoryJSON[] repositories = restTemplate.getForObject(url, RepositoryJSON[].class);

        if (repositories == null) {
            throw new RuntimeException("GitHub API response is null");
        }

        ArrayList<RepositoryDTO> validRepo = new ArrayList<>();

        for (RepositoryJSON repo : repositories) {
            if (repo != null && !repo.isFork()) {
                String repoName = repo.getName();
                String repoLogin = repo.getOwner().getLogin();
                List<BranchDTO> repoBranches = getBranches(username, repoName);
                validRepo.add(new RepositoryDTO(repoName, repoLogin, repoBranches));
            }
        }
        return validRepo;
    }

    public List<BranchDTO> getBranches(String username, String repoName) {
        String url = GITHUB_API_URL + "repos/" + username + "/" + repoName + "/branches";
        BranchJSON[] branches = restTemplate.getForObject(url, BranchJSON[].class);

        if (branches == null) {
            throw new RuntimeException("GitHub API response is null");
        }

        ArrayList<BranchDTO> validBranches = new ArrayList<>();

        for (BranchJSON branch : branches) {
            if (branch != null) {
                String branchName = branch.getName();
                String branchSha = branch.getCommit().getSha();
                validBranches.add(new BranchDTO(branchName, branchSha));
            }
        }
        return validBranches;
    }
}

