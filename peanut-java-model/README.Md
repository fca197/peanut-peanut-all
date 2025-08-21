# 项目说明

项目启动入口， 以tmp为包，避免项目出现多个启动main方法

[JavaModelApplication.java](src/main/java/tmp/JavaModelApplication.java)

# 启动说明

可以单独启动该模块， 启动类为： [JavaModelApplication.java](src/main/java/tmp/JavaModelApplication.java)  

可以启动多个模块， 启动类为： [BootstrapApplication.java](../peanut-gateway/src/main/java/com/olivia/BootstrapApplication.java)  

# pom.xml 说明

增加 配置文件复制，保留peanut-gateway一份配置文件，其他服务均为该文件副本

```xml
 <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-resources-plugin</artifactId>
        <version>3.3.1</version>
        <executions>
          <execution>
            <id>copy-peanut-gateway-resources</id>
            <phase>process-resources</phase>
            <goals>
              <goal>copy-resources</goal>
            </goals>
            <configuration>
              <!-- 目标目录：当前项目的资源输出目录（必须正确） -->
              <outputDirectory>${project.build.directory}/classes</outputDirectory>

              <!-- 源资源目录：peanut-gateway的resources路径（根据实际结构调整） -->
              <resources>
                <resource>
                  <!-- 示例1：如果当前项目和peanut-gateway是同级模块 -->
                  <directory>${project.basedir}/../peanut-gateway/src/main/resources</directory>

                  <!-- 示例2：如果peanut-gateway是父项目下的同级子模块 -->
                  <!-- <directory>${project.parent.basedir}/peanut-gateway/src/main/resources</directory> -->

                  <!-- 包含所有文件（不要遗漏） -->
                  <includes>
                    <include>**/*</include>
                  </includes>
                </resource>
              </resources>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>

```