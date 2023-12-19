FROM openjdk:17
WORKDIR /root
# 将当前目录下的web.jar包传入容器中重命名为demo.jar
ADD webProject*.jar demo.jar
EXPOSE 8081
# 启动容器时执行 java -jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]