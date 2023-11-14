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
                sh 'mvn sonar:sonar -Dsonar.login=Admin -Dsonar.password=Charfeddine55!'
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
                    def dockerImage = docker.build("inesmsallem/achat:latest")
                }
            }
        }
        stage('Deploy image') {
            steps {
                echo "Deploying the image..."
                sh 'docker login -u inesmsallem -p pewpew58'
                sh 'docker push inesmsallem/achat:latest'
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
