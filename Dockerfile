FROM openjdk:8
EXPOSE 8080
ADD target/toast.jar toast.jar
ENTRYPOINT ["java","-jar","/toast.jar"]
