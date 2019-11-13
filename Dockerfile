FROM jdk: 8


# Ferramentas de depuração: algumas maneiras de lidar com ferramentas de depuração.
# O trade-off é uma montagem de volume um pouco mais complexa do que manter o tamanho da imagem baixo.
RUN apt-get update && \
  apt-get install -y \
    net-tools \
    tree \
    vim && \
  rm -rf /var/lib/apt/lists/* && apt-get clean && apt-get purge

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh
