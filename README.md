# GitHub Data Retrieval Application

## Description

The application allows retrieving information about GitHub users' repositories and branches. It utilizes the GitHub API to fetch data.

## Controller

The application contains a controller named DataController. Below, you will find a brief description of its functions.

### Method `getInfo`

This method is accessible at the endpoint `/info/{username}` and allows retrieving information about the repositories of the user with the specified username.

#### Parameters:
- `username` (path variable): GitHub username.

#### Example Request::
```http
GET /info/johndoe

Sample Response:
[
    {
        "repoName": "example-repo",
        "repoLogin": "johndoe",
        "branches": [
            {
                "branchName": "main",
                "branchSha": "abc123"
            },
            {
                "branchName": "feature-branch",
                "branchSha": "def456"
            }
        ]
    },
    // additional repositories...
]
```
### Method `getRepositoriesByUser`
A private method used to retrieve a list of repositories for a specific user.

#### Parameters:
- `username` : GitHub username.

### Method `getBranches`
A private method used to retrieve a list of branches in a particular repository.

#### Parameters:
- `username` : GitHub username.
- `repoName` : Repository name.

## Running

- clone repository
- go to repository directory 
- run the application with the following command : `mvn spring-boot:run`

Ensure you have internet access as the application relies on the GitHub API.


**Note:** By default the application is available at `http://localhost:8080` with endpoint `/info/{username}`.