pipeline {
    agent any
 environment {
        DOCKER_REGISTRY = 'lindabelhadj'
        DOCKER_IMAGE_NAME = 'kaddem-project-linda'
        CONTAINER_NAME= 'devops-kaddem-project'
        DOCKER_IMAGE_TAG = 'latest'
        PORT="9095"
    }
    stages {

        stage('Hello Linda') {
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
        stage('testing spring') {
            steps {
              // sh "docker run -d -p 3308:3306 --name testing_container -e MYSQL_ROOT_PASSWORD=root mysql:5.7"
               sh 'mvn test -Dspring.profiles.active=testing'
            }
        }
        stage('Code Coverage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn jacoco:report"
                }
            }
        }
        stage('Quality test SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
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

        stage('Docker Build image') {
            steps {
              //  sh "mvn package -DskipTests"
              //  sh 'docker build -t lindabelhadj/kaddem:1-0 .'
               sh "docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} ."
            }
        }

        stage('Docker PUSH image') {
            steps {
                //sh 'docker login -u lindabelhadj -p dock@hello123!!Lin'
                //sh 'docker push lindabelhadj/kaddem:1-0'
                echo "Deploying the image..."
                sh 'docker login -u lindabelhadj -p dock@hello123!!Lin docker.io'
                sh 'docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} '
            }
        }

        stage("Docker compose") {
              steps {

                sh "docker compose up -d"
            }
        }

        stage("Removing testing container") {
            steps {
                 sh "docker stop testing_container"
                 sh "docker rm testing_container"
            }
        }
    }
    post {
        success {
            mail bcc: '',
            body: '''
            'Dear Lynda ,
            we are happy to inform you that your pipeline build was successful.
            Great work!
                -Jenkins Team - ''',
            cc: '',
            from: 'lynda.belhadj@esprit.tn',
            replyTo: '',
            subject: 'Build Finished - Success',
            to: 'lynda.belhadj@esprit.tn'
        }

        failure {
            mail bcc: '',
            body: '''
            'Dear Lynda,
            we are sorry to inform you that your pipeline build failed.
            Keep working!
                -Jenkins Team - ''',
            cc: '',
            from: 'lynda.belhadj@esprit.tn', replyTo: '',
            subject: 'Build Finished - Failure', to: 'lynda.belhadj@esprit.tn'
        }

        always {
            emailext attachLog: true, body: '', subject: 'Build finished', from: 'lynda.belhadj@esprit.tn', to: 'lynda.belhadj@esprit.tn'
            cleanWs()
        }
    }
}