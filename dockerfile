FROM maven:3.8.7-eclipse-temurin-11
WORKDIR /app
COPY . /app
RUN ls
RUN chmod +x /app/linuxdriver/chromedriver
RUN apt update
RUN apt install gnupg -y
RUN apt install apt-utils
# Adding trusting keys to apt for repositories
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
# Adding Google Chrome to the repositories
RUN sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
# Updating apt to see and install Google Chrome
RUN apt-get -y update
# Magic happens
RUN apt-get install -y google-chrome-stable
RUN chmod +x starter.sh
ENTRYPOINT ./starter.sh