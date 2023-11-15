pipeline {
    agent any

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
        stage('Deploy artifact with nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage("Building image") {
            steps {
                script {
                    def dockerImage = docker.build("191jft4255/khaddem:latest")
                }
            }
        }
        stage('Deploy image') {
            steps {
                echo "Deploying the image..."
                sh 'docker login -u 191jft4255 -p 191JFT4255'
                sh 'docker push 191jft4255/khaddem:latest'
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
