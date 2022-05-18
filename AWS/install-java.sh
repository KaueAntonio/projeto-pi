FROM ubuntu:latest

RUN add-apt-repository ppa:webupd8team/java -y
RUN apt update -y
RUN apt install default-jre ; apt install openjdk-11-jre-headless; -y
RUN git clone https://www.github.com/kaueantonio/projeto-pi.git
RUN cd AWS
RUN java -jar testeProjeto-SERVER.java
