# Alarmservice Demo Spring Example Application

### Generate models
```
java -jar src/openapi/generator-cli/openapi-generator-cli-7.2.0.jar generate --package-name io.sciota.demo.alarmservice.dtos -g spring --model-package io.sciota.demo.alarmservice.dtos --model-name-suffix dto -o ./ -i src/openapi/api.yml --global-property models
```

### Swagger
http://localhost:9000/swagger

### Actuator metrics
http://localhost:9000/actuator
