# Mini Projects Collection

## 🚀 Technologies Used

<p>
  <img src="image/icons8-react-80.png" alt="React" title="React" style="margin-right: 20px; height:50px">
  <img src="image/icons8-spring-boot-48.png" alt="Spring Boot" title="Spring Boot" style="margin-right: 20px; height:50px">
  <img src="image/icons8-mongodb-a-cross-platform-document-oriented-database-program-48.png" alt="MongoDB" title="MongoDB" style="height:50px">
</p>

## Important Notes

### 1. **Lombok Dependency for Maven**

Lombok is a popular Java library that helps reduce boilerplate code by automatically generating common methods such as getters, setters, constructors, `toString()`, `equals()`, `hashCode()`, and more. By using annotations, Lombok generates these methods at compile-time, allowing developers to focus more on business logic rather than repetitive code.

#### Key Features:

- **@Getter / @Setter**: Automatically generates getter and setter methods for fields.
- **@ToString**: Creates a `toString()` method to provide a string representation of the object.
- **@EqualsAndHashCode**: Generates `equals()` and `hashCode()` methods based on the class fields.
- **@Builder**: Implements the builder pattern for object creation.
- **@Slf4j**: Adds a logger to the class.
- **@NoArgsConstructor / @AllArgsConstructor**: Automatically generates constructors with no arguments or all arguments.

#### Benefits:

- **Reduces Boilerplate**: Minimizes repetitive code, making your classes cleaner and easier to read.
- **Improves Productivity**: Saves time by automating the generation of common methods.
- **Enhances Maintainability**: By reducing boilerplate, the codebase becomes easier to maintain and understand.

#### Lombok Maven Dependency:

To add Lombok to your Maven project, include the following dependency in your `pom.xml`:

```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.36</version>
  <scope>provided</scope>
</dependency>
```

### 2. **MongoDB Database Connection Dependency for Maven**

To connect to MongoDB from Spring Boot, include the following dependency in your `pom.xml`:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

### 3. **React API Handling Module Installation**

To manage API requests from your React frontend, you need to install **Axios**:

```bash
npm install axios
```

Then, import Axios in your React components (JSX files):

```javascript
import axios from "axios";
```

## 🔗 Links

- [React Documentation](https://reactjs.org/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Boot Anotation Details](https://docs.google.com/document/d/1jQ5SCUu2qKxffj6JgeH7rIPFUwGXJ9ljQbc6hfDnOyg/edit?usp=sharing);
- [MongoDB Documentation](https://www.mongodb.com/docs/)
