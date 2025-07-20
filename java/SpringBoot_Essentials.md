# Spring Boot 知识要点 (Essentials)

Spring Boot 是一个由 Pivotal 团队提供的开源框架，它基于 Spring 框架，旨在简化新 Spring 应用的初始搭建以及开发过程。通过大量“约定优于配置”的原则，Spring Boot 使开发者能够快速创建独立运行的、生产级别的基于 Spring 的应用程序。

## 1. Spring Boot 是什么？

*   **目标**:
    *   为所有Spring开发提供一个更快、更广泛的入门体验。
    *   开箱即用，但通过修改默认配置来快速适应需求变化。
    *   提供一系列大型项目中常见的非功能性特性，如内嵌服务器、安全、指标、健康检查和外部化配置。
    *   完全没有代码生成，也不需要XML配置。
*   **核心优点**:
    *   **约定优于配置 (Convention over Configuration)**: Spring Boot 对常用场景提供了合理的默认配置，开发者无需手动进行大量配置。
    *   **自动配置 (Auto-Configuration)**: 尝试根据项目中添加的依赖自动配置Spring应用和第三方库。
    *   **起步依赖 (Starters)**: 提供了一系列方便的依赖描述符，可以一次性引入开发特定功能所需的全部相关依赖，简化了Maven/Gradle配置。
    *   **内嵌服务器 (Embedded Servers)**: 内嵌了 Tomcat, Jetty, Undertow 等Servlet容器（无需部署WAR文件），使得应用可以作为独立的JAR运行。
    *   **生产就绪特性 (Production-ready Features)**: 通过 Spring Boot Actuator 提供监控和管理应用的功能，如健康检查、指标收集、HTTP跟踪等。

## 2. Spring Boot 核心特性详解

### 2.1 自动配置 (Auto-Configuration)

*   **是什么**: Spring Boot 的自动配置是其最强大的特性之一。它会尝试根据你项目 classpath（类路径）中存在的 JAR 包来自动配置你的 Spring 应用程序。例如，如果 `HSQLDB` 在你的 classpath 中，并且你没有手动配置任何数据库连接 beans，那么 Spring Boot 会自动配置一个内存中的数据库。
*   **如何工作**:
    *   Spring Boot 在 `spring-boot-autoconfigure.jar` 中的 `META-INF/spring.factories` (或更高版本中的 `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`) 文件里定义了大量的自动配置类 (`*AutoConfiguration`)。
    *   每个自动配置类通常使用 `@ConditionalOnClass`、`@ConditionalOnMissingBean` 等条件注解来判断是否应该应用该配置。例如，`DataSourceAutoConfiguration` 会检查 classpath 中是否有数据库驱动和连接池相关的类，并且当前 Spring 上下文中是否已经存在 `DataSource` 类型的 Bean。
    *   如果条件满足，自动配置类中的 `@Bean` 定义就会被加载，从而完成相关组件的自动配置。
*   **自定义与禁用**:
    *   开发者可以通过在 `application.properties` 或 `application.yml` 中设置属性来覆盖自动配置的默认值。
    *   可以使用 `@EnableAutoConfiguration` 注解的 `exclude` 或 `excludeName` 属性来禁用特定的自动配置类。
    *   例如: `@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})`

### 2.2 起步依赖 (Starters)

*   **是什么**: Starters 是一组方便的依赖描述符，你可以将它们包含在你的应用程序中。它们使得添加依赖项变得简单和一站式。你不再需要到处寻找和复制粘贴大量的依赖项配置。
*   **作用**:
    *   **简化依赖管理**: 只需要引入一个 starter，它会自动传递性地引入所需的所有相关库。例如，`spring-boot-starter-web` 会引入 Spring MVC, Jackson (JSON处理), Tomcat (内嵌服务器) 等。
    *   **版本一致性**: Spring Boot 管理了这些传递依赖的版本，确保它们之间兼容，避免了版本冲突的问题。这些版本信息定义在 `spring-boot-dependencies` 父POM中。
*   **命名约定**: 官方的 starter 都遵循 `spring-boot-starter-*` 的命名模式，其中 `*` 是一个特定类型的应用程序或功能。
*   **常用 Starters**:
    *   `spring-boot-starter-web`: 用于构建 Web 应用，包括 RESTful 应用，使用 Spring MVC。它默认使用 Tomcat 作为内嵌Servlet容器。
    *   `spring-boot-starter-webflux`: 用于构建响应式 Web 应用。
    *   `spring-boot-starter-data-jpa`: 用于使用 Spring Data JPA 和 Hibernate 进行数据库持久化。
    *   `spring-boot-starter-data-mongodb`: 用于使用 Spring Data MongoDB。
    *   `spring-boot-starter-test`: 用于测试 Spring Boot 应用，包含 JUnit, Hamcrest, Mockito, Spring Test 等。
    *   `spring-boot-starter-security`: 用于使用 Spring Security 进行认证和授权。
    *   `spring-boot-starter-actuator`: 用于添加生产环境监控和管理功能。
    *   `spring-boot-starter-thymeleaf`, `spring-boot-starter-freemarker`: 用于集成相应的模板引擎。

### 2.3 Spring Boot CLI (命令行界面)

*   **是什么**: Spring Boot CLI 是一个命令行工具，如果你想快速用 Spring 进行原型开发，可以使用它。它允许你运行 Groovy 脚本，这意味着你可以拥有熟悉的 Java 式语法，而无需太多的样板代码。
*   **用途**:
    *   快速原型开发。
    *   运行 Groovy 脚本，自动处理依赖下载和配置。
    *   可以用来执行一些 Spring Boot 命令，如 `spring run`, `spring test`, `spring jar`。
*   **注意**: 对于大型或正式项目，通常还是会使用 Maven 或 Gradle 构建系统。CLI 更适合快速实验。

### 2.4 Spring Boot Actuator (监控与管理)

*   **是什么**: Spring Boot Actuator 提供了生产就绪的特性，帮助你监控和管理你的应用程序。当你的应用部署到生产环境后，Actuator 可以让你了解应用的运行状况、收集指标、查看HTTP请求跟踪等。
*   **主要端点 (Endpoints)**: Actuator 通过 HTTP 端点 (或 JMX MBeans) 暴露这些信息。默认情况下，大部分端点需要通过 `management.endpoints.web.exposure.include` 属性来显式暴露。
    *   `/actuator/health`: 显示应用程序的健康状况 (UP, DOWN, OUT_OF_SERVICE)。可以集成自定义的健康指示器。
    *   `/actuator/info`: 显示任意的应用信息，可以在 `application.properties` 中配置 `info.*` 属性。
    *   `/actuator/metrics`: 显示应用的各种指标，如 JVM 内存使用、系统 CPU 使用、HTTP 请求计数和延迟等。
    *   `/actuator/loggers`: 显示和修改应用的日志级别。
    *   `/actuator/httptrace` (或旧版的 `/actuator/trace`): 显示最近的 HTTP 请求-响应交换信息。
    *   `/actuator/env`: 显示当前应用的环境属性。
    *   `/actuator/beans`: 显示应用中所有 Spring bean 的列表。
    *   `/actuator/mappings`: 显示所有 `@RequestMapping` 路径。
    *   `/actuator/configprops`: 显示所有 `@ConfigurationProperties` 的配置属性。
    *   `/actuator/threaddump`: 执行线程转储。
    *   `/actuator/heapdump`: 下载堆转储文件。
*   **安全性**: 生产环境中，Actuator 端点需要被保护，避免敏感信息泄露。可以集成 Spring Security 来保护这些端点。

## 3. Spring Boot 常用模块/功能

Spring Boot 通过其 Starters 和自动配置机制，极大地简化了对各种常用技术和框架的集成。

### 3.1 Web 开发

Spring Boot 非常适合用于快速开发 Web 应用程序，包括传统的服务端渲染应用和现代的 RESTful API。

*   **Spring MVC / Spring WebFlux**:
    *   **`spring-boot-starter-web`**: 默认情况下，此 starter 用于构建基于 Servlet API 的 Web 应用，集成了 Spring MVC。它会自动配置 DispatcherServlet、视图解析器等。
    *   **`spring-boot-starter-webflux`**: 用于构建响应式 Web 应用，基于 Project Reactor 和 Netty。适用于高并发、非阻塞IO的场景。
*   **RESTful API 开发**:
    *   使用 `@RestController` 注解将一个类标记为处理HTTP请求的控制器，其方法默认返回数据（如JSON/XML）而不是视图名。
    *   常用注解：
        *   `@RequestMapping("/path")`: 映射HTTP请求到处理方法。
        *   `@GetMapping("/path")`, `@PostMapping("/path")`, `@PutMapping("/path")`, `@DeleteMapping("/path")`, `@PatchMapping("/path")`: 特定HTTP方法的快捷映射。
        *   `@PathVariable`: 从URL路径中提取变量。
        *   `@RequestParam`: 从请求参数中提取值。
        *   `@RequestBody`: 将HTTP请求体的内容（如JSON）绑定到方法参数。
        *   `@ResponseBody` (在 `@RestController` 中是默认行为): 表示方法返回值应直接写入HTTP响应体。
        *   `ResponseEntity<T>`: 更灵活地控制HTTP响应，包括状态码、头部和响应体。
*   **内嵌服务器**:
    *   Spring Boot 默认内嵌 Tomcat 服务器。可以通过修改依赖切换到 Jetty 或 Undertow。
    *   无需将应用打包成 WAR 文件部署到外部服务器，直接运行 `main` 方法或可执行JAR即可启动。
*   **JSON/XML 数据处理**:
    *   当 classpath 中有 Jackson (JSON) 或 JAXB (XML) 库时，Spring Boot 会自动配置相应的 `HttpMessageConverter` 来处理请求和响应中的数据转换。
    *   Jackson 是默认的JSON处理库。

### 3.2 数据访问

Spring Boot 提供了多种与数据库交互的方式。

*   **Spring Data JPA**:
    *   通过 `spring-boot-starter-data-jpa` 引入。
    *   简化了 JPA (Java Persistence API) 的使用，通常与 Hibernate 作为持久化提供者一起工作。
    *   核心概念：
        *   **Entity (`@Entity`)**: 映射到数据库表的Java对象。
        *   **Repository (继承 `JpaRepository<Entity, ID>`)**: 定义数据访问接口，Spring Data JPA 会自动实现基本的CRUD操作和基于方法名的查询。
        *   **自动配置**: Spring Boot 会自动配置 `DataSource`, `EntityManagerFactory` 等。只需在 `application.properties` 中配置数据库连接信息。
*   **JDBC (Java Database Connectivity)**:
    *   如果不想使用 ORM 框架，可以使用 `spring-boot-starter-jdbc`。
    *   Spring Boot 会自动配置 `DataSource` 和 `JdbcTemplate`。
    *   `JdbcTemplate` 提供了便捷的方法来执行SQL查询和更新，处理结果集映射等，避免了冗余的JDBC代码。
*   **NoSQL 数据库**:
    *   Spring Boot 也为多种 NoSQL 数据库提供了 Starters 和自动配置，如：
        *   `spring-boot-starter-data-mongodb` (MongoDB)
        *   `spring-boot-starter-data-redis` (Redis)
        *   `spring-boot-starter-data-elasticsearch` (Elasticsearch)
    *   使用方式与 Spring Data JPA 类似，通过 Repository 接口进行数据操作。

### 3.3 配置管理

Spring Boot 提供了灵活的外部化配置机制。

*   **`application.properties` 或 `application.yml`**:
    *   这是主要的配置文件，位于 `src/main/resources` 目录下。
    *   `.properties` 文件使用键值对格式 (`server.port=8080`)。
    *   `.yml` (或 `.yaml`) 文件使用 YAML 格式，更具结构化和可读性。Spring Boot 会自动识别并加载。
*   **Profile 特定配置**:
    *   可以为不同的环境（如开发、测试、生产）创建不同的配置文件，例如 `application-dev.properties`, `application-prod.yml`。
    *   通过激活特定的 profile (如 `spring.profiles.active=dev`) 来加载相应的配置。激活的profile配置会覆盖默认配置。
*   **`@ConfigurationProperties` 注解**:
    *   允许将配置文件中的属性以类型安全的方式绑定到Java对象的字段上。
    *   例如，可以创建一个类，其字段对应于 `my.app.*` 下的一组配置。
    *   通常与 `@Component` 或 `@EnableConfigurationProperties` 一起使用。
*   **其他配置源**: Spring Boot 还可以从命令行参数、环境变量、JNDI属性等多种来源加载配置，并有明确的优先级顺序。

### 3.4 模板引擎 (简要提及)

虽然 Spring Boot 常用于构建 REST API，但它也支持服务端模板引擎用于生成HTML。

*   **常用 Starters**:
    *   `spring-boot-starter-thymeleaf`
    *   `spring-boot-starter-freemarker`
    *   `spring-boot-starter-mustache`
    *   `spring-boot-starter-groovy-templates`
*   引入相应的 starter 后，Spring Boot 会自动配置视图解析器。只需将模板文件放在 `src/main/resources/templates` 目录下。

### 3.5 安全 (Spring Security)

*   通过 `spring-boot-starter-security` 引入 Spring Security。
*   **自动配置**: Spring Boot 会自动配置基本的安全策略，如启用HTTP Basic认证、表单登录，并防止CSRF攻击。
*   **自定义**: 可以通过创建 `WebSecurityConfigurerAdapter` (在较新版本中推荐使用 `SecurityFilterChain` bean) 的配置类来完全自定义安全规则，如配置用户认证方式、URL授权规则、密码编码器等。
*   提供了对 OAuth2, JWT, LDAP 等常见安全方案的支持。

### 3.6 测试

Spring Boot 提供了强大的测试支持。

*   **`spring-boot-starter-test`**: 这个 starter 引入了常用的测试库：
    *   **JUnit 5 (或 JUnit 4)**: Java单元测试框架。
    *   **Spring Test & Spring Boot Test**: 提供Spring集成测试支持。
    *   **AssertJ**: 流畅的断言库。
    *   **Hamcrest**: 用于编写匹配器对象的库。
    *   **Mockito**: Java Mocking框架，用于模拟依赖。
    *   **JSONassert**: 用于JSON的断言库。
    *   **JsonPath**: 用于JSON的XPath表达式。
*   **`@SpringBootTest` 注解**:
    *   用于加载完整的Spring应用上下文进行集成测试。
    *   可以配置 `webEnvironment` 属性来测试Web层 (如 `RANDOM_PORT` 或 `MOCK`)。
*   **Mocking**:
    *   使用 `@MockBean` 注解可以在Spring上下文中替换一个bean为其Mockito mock对象。
    *   使用 `@SpyBean` 注解可以监视一个真实的bean。
*   **测试 REST API**:
    *   **`MockMvc`**: 用于对Spring MVC控制器进行服务端集成测试，不实际启动HTTP服务器，模拟HTTP请求和响应。
    *   **`TestRestTemplate`**: 用于进行基于真实HTTP服务器的集成测试（当 `@SpringBootTest` 的 `webEnvironment` 设置为 `RANDOM_PORT` 或 `DEFINED_PORT` 时）。
*   **切片测试 (Slice Tests)**:
    *   Spring Boot 提供了一些专门的测试注解，用于只测试应用的一部分（一个“切片”），例如：
        *   `@WebMvcTest`: 只测试MVC控制器层，不加载整个应用上下文。
        *   `@DataJpaTest`: 只测试JPA Repositories。
        *   `@JsonTest`: 只测试JSON序列化/反序列化。

## 4. Spring Boot 项目结构和关键文件

一个典型的Spring Boot项目（无论是使用Maven还是Gradle构建）通常遵循标准的目录结构。

### 4.1 典型项目结构

```
my-spring-boot-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/myapp/  // Java源代码根目录
│   │   │       └── MySpringBootApplication.java // 主应用程序类
│   │   │       ├── controller/     // 控制器 (REST APIs, Web)
│   │   │       ├── service/        // 业务逻辑服务
│   │   │       ├── repository/     // 数据访问层 (JPA Repositories)
│   │   │       └── domain/         // 领域模型 (Entities)
│   │   ├── resources/              // 资源文件根目录
│   │   │   ├── static/             // 静态资源 (CSS, JavaScript, 图片)
│   │   │   ├── templates/          // 服务端模板 (Thymeleaf, FreeMarker)
│   │   │   └── application.properties  // 应用配置文件 (或 application.yml)
│   └── test/
│       └── java/
│           └── com/example/myapp/  // 测试代码根目录
│               └── MySpringBootApplicationTests.java // 测试类
├── .gitignore                  // Git忽略文件配置
├── pom.xml                     // Maven项目配置文件 (或 build.gradle for Gradle)
└── README.md                   // 项目说明文件
```

*   **`src/main/java`**: 存放应用的Java源代码。
*   **`src/main/resources`**: 存放所有非代码资源。
    *   `static` 目录: 用于存放静态内容，如HTML, CSS, JavaScript, 图片等。Spring Boot会自动从这里提供静态资源服务。
    *   `templates` 目录: 用于存放服务端渲染的模板文件（如Thymeleaf, FreeMarker）。
    *   `application.properties` (或 `.yml`): 主要的外部化配置文件。
*   **`src/test/java`**: 存放单元测试和集成测试的Java代码。
*   **`pom.xml` (Maven)** 或 **`build.gradle` (Gradle)**: 项目构建和依赖管理文件。

### 4.2 `@SpringBootApplication` 注解

这个注解通常用在你的主应用程序类（包含 `main` 方法的类）上。它是一个方便的组合注解，包含了以下三个核心注解的功能：

*   **`@SpringBootConfiguration`**:
    *   将该类标记为一个Spring Boot配置类。它实际上是 `@Configuration` 注解的特定形式。
    *   允许Spring自动扫描和注册该类中定义的Bean。
*   **`@EnableAutoConfiguration`**:
    *   告诉Spring Boot根据项目中添加的依赖“猜测”并自动配置应用程序。这是Spring Boot自动配置魔法的核心。
    *   如前所述，可以通过 `exclude` 属性排除特定的自动配置。
*   **`@ComponentScan`**:
    *   启用组件扫描。Spring Boot会自动扫描该注解所在类及其子包下的组件（如 `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController` 等），并将它们注册为Spring Beans。
    *   默认情况下，它会扫描与 `@SpringBootApplication` 注解所在类相同包及其子包。可以自定义扫描的包路径。

**示例:**
```java
package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 等同于 @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
```

### 4.3 构建文件 (`pom.xml` / `build.gradle`)

*   **Spring Boot Parent POM (Maven)**:
    *   在Maven项目中，通常会继承 `spring-boot-starter-parent`。
    *   ```xml
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.7.0</version> <!-- 请使用最新的稳定版本 -->
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
      ```
    *   这个父POM提供了很多有用的默认配置：
        *   **依赖管理**: 管理了大量常用库的版本，确保它们之间的兼容性。你引入Spring Boot Starters时通常无需指定版本号。
        *   **默认Java版本**: 通常配置为较新的LTS版本。
        *   **默认插件配置**: 如 `spring-boot-maven-plugin`。
*   **Spring Boot Gradle Plugin (Gradle)**:
    *   在Gradle项目中，会应用 `org.springframework.boot` 插件。
    *   ```gradle
      plugins {
          id 'org.springframework.boot' version '2.7.0' // 请使用最新的稳定版本
          id 'io.spring.dependency-management' version '1.0.11.RELEASE'
          id 'java'
      }
      ```
    *   该插件提供了类似Maven父POM的功能，包括依赖管理和打包可执行JAR/WAR的能力。
*   **引入Starters**:
    *   无论是Maven还是Gradle，你都会通过添加 `spring-boot-starter-*` 依赖来引入特定功能。
    *   **Maven示例 (`pom.xml`)**:
        ```xml
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
        </dependencies>
        ```
    *   **Gradle示例 (`build.gradle`)**:
        ```gradle
        dependencies {
            implementation 'org.springframework.boot:spring-boot-starter-web'
            testImplementation 'org.springframework.boot:spring-boot-starter-test'
        }
        ```
*   **`spring-boot-maven-plugin` / Spring Boot Gradle Plugin**:
    *   这些构建插件非常重要，它们负责将Spring Boot应用打包成一个可执行的JAR文件（或传统的WAR文件）。
    *   这个可执行JAR包含了所有依赖以及内嵌的Servlet容器，可以通过 `java -jar myapp.jar` 直接运行。

## 5. Spring Boot 开发技巧和最佳实践 (选讲)

除了核心功能外，还有一些工具和实践可以进一步提升Spring Boot的开发效率和体验。

### 5.1 使用 Lombok 简化代码

*   **Lombok 是什么**: Project Lombok 是一个Java库，它能自动插入到你的编辑器和构建工具中，帮助你消除Java的冗长代码。只需通过简单的注解，就可以替代大量的样板代码，如getter/setter, `toString()`, `equals()`, `hashCode()`, 构造函数等。
*   **如何使用**:
    1.  **添加依赖**:
        *   Maven (`pom.xml`):
            ```xml
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <optional>true</optional> <!-- 通常设为optional，因为它主要在编译期起作用 -->
            </dependency>
            ```
        *   Gradle (`build.gradle`):
            ```gradle
            compileOnly 'org.projectlombok:lombok'
            annotationProcessor 'org.projectlombok:lombok' // 或者 testCompileOnly, testAnnotationProcessor
            ```
    2.  **IDE支持**: 大部分主流IDE（如IntelliJ IDEA, Eclipse, VS Code）需要安装Lombok插件才能正确识别和处理Lombok注解，避免在编辑器中看到错误提示。
*   **常用注解**:
    *   `@Getter` / `@Setter`: 自动生成getter和setter方法。
    *   `@ToString`: 自动生成`toString()`方法。
    *   `@EqualsAndHashCode`: 自动生成`equals()`和`hashCode()`方法。
    *   `@NoArgsConstructor`, `@RequiredArgsConstructor`, `@AllArgsConstructor`: 自动生成不同类型的构造函数。
    *   `@Data`: 相当于 `@Getter @Setter @ToString @EqualsAndHashCode @RequiredArgsConstructor` 的组合。
    *   `@Builder`: 实现建造者模式，方便创建对象。
    *   `@Slf4j` / `@Log4j2` 等: 自动生成日志记录器实例。
*   **示例**:
    ```java
    import lombok.Data;
    import lombok.extern.slf4j.Slf4j;

    @Data // 自动生成getter, setter, toString, equals, hashCode, RequiredArgsConstructor
    @Slf4j
    public class User {
        private final String username; // final字段会包含在RequiredArgsConstructor中
        private int age;
        private String email;

        public void doSomething() {
            log.info("User {} is doing something.", username); //可以直接使用log
        }
    }
    ```

### 5.2 使用 Spring Boot DevTools 提升开发效率

*   **Spring Boot DevTools 是什么**: `spring-boot-devtools` 是一个模块，它提供了一系列旨在提升开发体验的工具。当你的应用运行在开发模式下时，DevTools 会启用一些有用的功能。
*   **如何引入**:
    *   Maven (`pom.xml`):
        ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        ```
    *   Gradle (`build.gradle`):
        ```gradle
        developmentOnly 'org.springframework.boot:spring-boot-devtools'
        ```
    *   **注意**: 当你构建一个完全打包的可执行JAR/WAR用于生产时，DevTools 会被自动禁用。
*   **核心功能**:
    *   **自动重启 (Automatic Restart)**:
        *   当 classpath 中的文件发生变化时（如修改 `.java` 或 `.properties` 文件并重新编译），DevTools 会自动重新启动应用程序。
        *   这比手动停止和重启应用要快得多，因为重启通常只重新加载已更改的部分。
        *   需要IDE配置：确保IDE在保存时自动编译文件。IntelliJ IDEA 中，需要开启 "Build project automatically" 并通过快捷键 (Ctrl+Shift+A 或 Cmd+Shift+A) 搜索 "Registry..."，然后启用 `compiler.automake.allow.when.app.running`。
    *   **LiveReload**:
        *   DevTools 内嵌了一个 LiveReload 服务器。当资源文件（如HTML, CSS, JavaScript）发生变化时，它可以触发浏览器自动刷新页面。
        *   需要在浏览器中安装 LiveReload 插件。
    *   **属性默认值**:
        *   为一些模板引擎（如 Thymeleaf）禁用了缓存，以便在开发时立即看到更改。
        *   为 `debug` 模式提供了更详细的日志记录。
    *   **全局设置**: 可以通过在用户主目录下创建 `.spring-boot-devtools.properties` 文件来配置全局的DevTools设置。

### 5.3 构建可执行 JAR / WAR

Spring Boot 应用通常被打包成一个可执行的 JAR 文件，这使得部署和运行非常方便。

*   **可执行 JAR (Executable JAR)**:
    *   这是Spring Boot的推荐打包方式。
    *   使用 `spring-boot-maven-plugin` (for Maven) 或 `bootJar` 任务 (for Gradle) 来构建。
    *   Maven: `mvn package`
    *   Gradle: `gradle bootJar` (或 `gradle build` 通常也会触发 `bootJar`)
    *   生成的JAR文件（通常在 `target` 或 `build/libs` 目录下）包含了所有应用的依赖项以及一个内嵌的Servlet容器（如Tomcat）。
    *   可以直接通过 `java -jar myapp.jar` 命令运行。
    *   **原理**: Spring Boot 使用了一种特殊的JAR布局，并通过一个自定义的 `JarLauncher` 来加载嵌套的依赖JAR和启动应用。
*   **传统的 WAR 包 (Deployable WAR)**:
    *   如果需要将Spring Boot应用部署到传统的外部Servlet容器（如独立的Tomcat, JBoss, WebSphere），也可以将其打包成WAR文件。
    *   **步骤**:
        1.  修改打包方式：
            *   Maven (`pom.xml`): 将 `<packaging>jar</packaging>` 改为 `<packaging>war</packaging>`。
            *   Gradle (`build.gradle`): 应用 `war` 插件。
        2.  将内嵌的Servlet容器依赖范围设为 `provided` (因为外部容器会提供)。
            *   Maven:
                ```xml
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                    <scope>provided</scope>
                </dependency>
                ```
        3.  确保主应用类继承自 `SpringBootServletInitializer` 并覆盖 `configure` 方法。
            ```java
            import org.springframework.boot.builder.SpringApplicationBuilder;
            import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

            @SpringBootApplication
            public class MyApplication extends SpringBootServletInitializer {

                @Override
                protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
                    return application.sources(MyApplication.class);
                }

                public static void main(String[] args) {
                    SpringApplication.run(MyApplication.class, args);
                }
            }
            ```
    *   构建命令与JAR包类似 (`mvn package` 或 `gradle build`)。
*   **选择 JAR 还是 WAR**:
    *   **JAR**: 更符合微服务和云原生应用的理念，易于部署、扩展和管理。是Spring Boot的首选。
    *   **WAR**: 适用于必须部署到现有外部Servlet容器的场景。

## 6. 进阶学习资源

本文档总结了 Spring Boot 的核心要点，希望能帮助你快速入门。要更深入地学习 Spring Boot，以下资源非常有价值：

*   **Spring Boot 官方参考文档 (英文)**:
    *   [最新稳定版文档](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - 这是最权威、最全面的学习资料，覆盖所有特性和配置选项。
*   **Spring Boot 官方指南 (英文)**:
    *   [Guides](https://spring.io/guides) - 提供了大量针对特定任务的实战教程，例如构建 RESTful Web 服务、使用 JPA 访问数据等。
*   **Spring Framework 官方文档 (英文)**:
    *   [最新稳定版文档](https://docs.spring.io/spring-framework/docs/current/reference/htmlsingle/) - 理解 Spring Boot 的基础是 Spring Framework 本身，深入学习 Spring Core, Spring MVC, Spring Data 等模块对掌握 Spring Boot 大有裨益。
*   **Baeldung 网站 (英文)**:
    *   [Spring Boot Tutorials](https://www.baeldung.com/spring-boot) - Baeldung 网站有大量高质量的 Spring 和 Spring Boot 教程，内容翔实且更新及时。

希望这些资源能帮助你在 Spring Boot 的学习道路上更进一步！
