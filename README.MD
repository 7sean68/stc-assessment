# Rest endpoints

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# Graphql
http://localhost:8080/graphiql
## sample

```
{
    getFile(id: 4, email: "user@stc.com") {
        id,
        name,
        type
    }
}
```

# Initial Data

- admin@stc.com UPDATE permission
- user@stc.com VIEW permission
