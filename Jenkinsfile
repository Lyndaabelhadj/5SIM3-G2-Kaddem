pipeline {
    agent {label "vagrant-agent"}

    environment {
        DOCKER_REGISTRY = '192.168.56.3:8085'
        DOCKER_IMAGE_NAME = 'image-kaddem-project-mehdi'
        CONTAINER_NAME= 'container-kaddem-project-mehdi'
        DOCKER_IMAGE_TAG = 'latest'
        PORT = '9090'
    }

    stages {

        stage('Hello Wissal') {
               steps {
                   echo 'Hello World'
               }
           }
           stage('Clean') {
               steps {
                   echo 'Cleaning...'
                   sh "mvn clean"
               }
           }
           stage('Build') {
               steps {
                   echo 'Validating...'
                   sh "mvn validate"
                   echo 'Compiling...'
                   sh "mvn compile"
               }
           }
        stage('Build Maven Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Sonar tests'){
            steps{
                sh "mvn sonar:sonar -Dsonar.login=squ_ad59a0e58c7f21b4f6079372f380d96526b86c8d"
            }
        }

        stage('Build Docker image') {
            steps {
                sh "sudo docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
            }
        }

        stage('Maven deploy'){
            steps{
                sh "mvn deploy -DskipTests"
            }
        }

        stage('Push Docker image to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus-credential-id', passwordVariable: 'password', usernameVariable: 'username')]) {
                    sh "sudo docker login -u ${username} -p ${password} ${DOCKER_REGISTRY}"
                    echo 'Pushing Docker Image to Nexus'
                    sh "sudo docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }


    }
    post {
        success {
            emailext body: "The pipeline has completed successfully",
                attachLog: true,
                subject: "Jenkins pipeline completed successfully",
                to: "touati.wissal@esprit.tn"
        }Jenkinsfile
        failure {
            emailext body: "The pipeline has failed",
                attachLog: true,
                subject: "Jenkins pipeline failed",
                to: "touati.wissal@esprit.tn"
        }
    }
}