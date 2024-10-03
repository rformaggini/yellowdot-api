#!/bin/bash
#install docker
    sudo apt-get update -y &&
    sudo apt-get install -y \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg-agent \
        software-properties-common &&
        curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - &&
    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" &&
    sudo apt-get update -y &&
    sudo sudo apt-get install docker-ce docker-ce-cli containerd.io -y &&
    sudo usermod -aG docker ubuntu
    sudo systemctl enable docker.service
    sudo systemctl enable containerd.service

#install docker compose
    #sudo apt install docker-compose -y

#access user root
    sudo su

#create directory for runner
    mkdir actions-runner && cd actions-runner

#download runner
    curl -o actions-runner-linux-x64-2.319.1.tar.gz -L https://github.com/actions/runner/releases/download/v2.319.1/actions-runner-linux-x64-2.319.1.tar.gz

#extract runner
    tar xzf ./actions-runner-linux-x64-2.319.1.tar.gz

#configuring runner to conect EC2
    RUNNER_ALLOW_RUNASROOT=true ./config.sh --url https://github.com/rformaggini/yellowdot-api --token AMTFRHFFSF6U2TMK4KIUTM3G73JVQ

#installs svc agent job
    sudo ./svc.sh install

#start the listing job
    sudo ./svc.sh start