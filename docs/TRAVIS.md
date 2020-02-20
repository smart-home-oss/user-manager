Integrate travis with sonarcloud:
- [travis :: encryption keys](https://docs.travis-ci.com/user/encryption-keys/)
- [CI/CD springboot applications using travis](https://sivalabs.in/2018/01/ci-cd-springboot-applications-using-travis-ci/)
- [Getting started with spring boot travis and heroku](https://medium.com/@felippepuhle/getting-started-with-spring-boot-travis-and-heroku-4562a723fd0e)

`travis encrypt SONAR_KEY_SECURE="222...9999" --pro`

Then we can use `SONAR_KEY_SECURE` as variable in our config:
```bash
...
addons:
  sonarcloud:
    organization: "smart-home-oss"
    token:
      secure: $SONAR_KEY_SECURE   <------- HERE
...
```

> `--pro` because we use travis.__com__ 