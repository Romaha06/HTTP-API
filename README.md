#Установка Maven проекта и запуск API Test:

1. Открыть среду разработки Intellij Idea
2. Создань новый Maven Проект
3. Добавляем dependency в pom.xml фаил 

dependency Rest Assured 

    <dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>rest-assured</artifactId>
      <version>4.1.2</version>
      <scope>test</scope>
    </dependency>

dependency Hamcrest

    <dependency>
       <groupId>org.hamcrest</groupId>
       <artifactId>hamcrest-library</artifactId>
       <version>1.3</version>
       <scope>test</scope>
    </dependency>
    <dependency>
       <groupId>org.hamcrest</groupId>
       <artifactId>hamcrest-core</artifactId>
       <version>2.2</version>
       <scope>test</scope>
    </dependency>

properties

    <properties>
       <aspectj.version>1.8.10</aspectj.version>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
     </properties> 

dependency Allure-testng     

    <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-testng</artifactId>
       <version>2.12.0</version>
    </dependency>

plugins для Allure    

    <build>
             <plugins>
                 <plugin>
                     <groupId>org.apache.maven.plugins</groupId>
                     <artifactId>maven-compiler-plugin</artifactId>
                     <configuration>
                         <source>1.8</source>
                         <target>1.8</target>
                     </configuration>
                 </plugin>
                 <plugin>
                     <groupId>org.apache.maven.plugins</groupId>
                     <artifactId>maven-surefire-plugin</artifactId>
                     <version>2.20</version>
                     <configuration>
                         <suiteXmlFiles>
                             <suiteXmlFile>TestNG.xml</suiteXmlFile>
                         </suiteXmlFiles>
                         <argLine>
                             -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                         </argLine>
                     </configuration>
                     <dependencies>
                         <dependency>
                             <groupId>org.aspectj</groupId>
                             <artifactId>aspectjweaver</artifactId>
                             <version>${aspectj.version}</version>
                         </dependency>
                     </dependencies>
                 </plugin>
             </plugins>
         </build>                 
         
dependency TestNG
 
    <dependency>
       <groupId>org.testng</groupId>
       <artifactId>testng</artifactId>
       <version>6.14.3</version>
    </dependency>

dependency Selenium

     <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>3.141.59</version>
     </dependency>

dependency WebDriverManager 

    <dependency>
       <groupId>io.github.bonigarcia</groupId>
       <artifactId>webdrivermanager</artifactId>
       <version>3.7.1</version>
       <scope>compile</scope>
    </dependency>
    
###Pom.xml fail готов   

Далее необходимо создать класс в папке Main => Test => Java
Вставить в него код из проекта на gitHub
Запустить код