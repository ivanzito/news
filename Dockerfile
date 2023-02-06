FROM ibm-semeru-runtimes:open-17-jdk-focal AS news

LABEL br.com.ivan.news.authors="Ivan Rodrigues"
LABEL email="ivan.rodrigues.da.silva.junior@gmail.com"

COPY . /opt/news

RUN echo "Installing gradle and unzip" && \
    apt update && apt install unzip && \
    curl -X GET https://downloads.gradle-dn.com/distributions/gradle-7.6-bin.zip -o /opt/gradle-7.6.zip && \
    unzip /opt/gradle-7.6.zip -d /opt/ && \
    rm /opt/gradle-7.6.zip && \
    export PATH=$PATH:/opt/gradle-7.6/bin && \
    cd /opt/news && \
    echo "Creating fat jar" && \
    gradle clean && gradle bootJar

ENTRYPOINT ["java","-jar","/opt/news/application/build/libs/application.jar"]

EXPOSE 8080

