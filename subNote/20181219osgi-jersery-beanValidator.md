# [目录](../directory.md)
#### 说明
1.在osgi中，Jersey结合hibernate-validator做参数校验;  
2.目前验证结果，只能在web模块使用  
3.在某些环境中，如果pom中涉及到的依赖不能下载到，则需要手动下载放到本地仓库中  
4.hibernate-validator只验证了5.1.3.Final版本，后续希望验证最新版  
5.校验不通过时，如果项目没约定输出，可以直接抛出异常

#### 参考文献
* [hibernate-validator](https://blog.csdn.net/quan20111992/article/details/80906334)


#### 代码
* pom
```
<dependencies>
    <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.1.3.Final</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml</groupId>
            <artifactId>classmate</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.thoughtworks.paranamer</groupId>
            <artifactId>paranamer</artifactId>
            <version>2.6</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.persistence</artifactId>
            <version>10.0-b28</version>
        </dependency>
        <dependency>
            <groupId>org.jboss.logging</groupId>
            <artifactId>jboss-logging</artifactId>
            <version>3.1.3.GA</version>
        </dependency>
        <dependency>
            <groupId>org.jboss.logmanager</groupId>
            <artifactId>jboss-logmanager</artifactId>
            <version>1.2.0.GA</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.7.2</version>
    </dependency>
</dependencies>

<Embed-Dependency>
    hibernate-validator;
    classmate;
    paranamer;
    javax.persistence;
    jboss-logging;
    boss-logmanager;
    jsoup;
</Embed-Dependency>
```
* 工具类
```
public final class Util {
    public static ResultBean validate(Object value) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ResultBean resultBean = new ResultBean(200, null);
        Set<ConstraintViolation<Object>> violationSet = validator.validate(value);
        if (violationSet.size() != 0) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Object> violation : violationSet) {
                builder.append(violation.getPropertyPath());
                builder.append(":");
                builder.append(violation.getMessage());
                builder.append(";");
            }
            resultBean.setCode(507);
            resultBean.setMessage(builder.toString());
        }
        return resultBean;
    }
}
```

* Bean
```
    @NotNull()
    @Max(64)
    @Min(1)
    @JsonProperty("tenant_id")
    private String tenantId;
```