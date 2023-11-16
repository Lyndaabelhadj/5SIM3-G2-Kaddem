pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = '191jft4255'
        DOCKER_IMAGE_NAME = 'kaddem-project-dali'
        CONTAINER_NAME = 'devops-kaddem-project'
        DOCKER_IMAGE_TAG = 'latest'
        PORT = "9095"
    }

    stages {
        stage('Hello Dali') {
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

        stage("Mockito") {
            steps {
                sh 'mvn test'
            }
        }

        stage('Quality test SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=Dali -Dsonar.password=191JFT4255'
                echo 'mvn -Sonarqube Analysis'
            }
        }

        stage('Maven Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy artifact with nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }

        stage('Deploy image') {
            steps {
                 echo "Deploying the image..."
                   sh 'docker login -u 191jft4255 -p 191JFT4255 docker.io'
                   sh 'docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} '
            }
        }

        stage('Deploy image') {
            steps {
                echo "Deploying the image..."
                script {
                    sh "docker login -u 191jft4255 -p 191JFT4255 192.168.50.12:8082"
                    sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                }
            }
        }


        stage('Run Docker Compose') {
            steps {
                echo "Running docker compose..."
                sh 'docker-compose up -d'
            }
        }
    }
}
